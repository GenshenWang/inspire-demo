package com.wgs.parrot.distributed_lock_util_004;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * 加锁过程：
 *  利用数据库lock_name的唯一性来判断加锁是否成功；
 *      先加锁：数据库插入lock_name=key 的一行数据；
 *      删除锁：插入成功之后，需主动删除lock_name=key的行数据，可放在success实现中
 *
 */
@Slf4j
public class MySqlDistributedLock implements DistributedLock {

    private final DataSource dataSource;
    private final String tableName;


    public MySqlDistributedLock(DataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.tableName = tableName;
    }

    @Override
    public boolean lock(String key, int waitTime, TimeUnit timeUnit) {
        boolean tryLock = false;
        try {
            tryLock = doLock(key);
            return tryLock;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void lock(String key, int waitTime, TimeUnit timeUnit, Runnable success, Runnable fail) {
        doLock(key, success, fail);
    }

    @Override
    public void unlock(String key) {
        if (dataSource == null) {
            throw new RuntimeException("MySqlDistributedLock dataSource is null");
        }

        Connection conn = getConnection();
        PreparedStatement ps = null;

        try {
            releaseLock(key, conn);
        } catch (SQLException e) {
           throw new RuntimeException("Unlock failed lock: " + key);
        } finally {
            closeConnection(conn);
            closePreparedStatement(ps);
        }
    }

    private Connection getConnection() {
        Connection conn;
        try {
            conn = this.dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Fetch mysql connection exception " + e.toString());
        }
        return conn;
    }

    private boolean doLock(String key,  Runnable success, Runnable fail) throws RuntimeException {
        if (dataSource == null) {
            throw new RuntimeException("MySqlDistributedLock dataSource is null");
        }

        PreparedStatement ps = null;
        Connection conn = getConnection();

        boolean tryLock = false;
        try {
            tryLock = addLock(key, conn);
            if (!tryLock) {
                fail.run();
            }

            success.run();
        } catch (Exception e) {
            fail.run();
        } finally {
            if (tryLock) {
                this.unlock(key);
            }
            closeConnection(conn);
            closePreparedStatement(ps);
        }
        return tryLock;
    }

    private boolean doLock(String key) throws RuntimeException {
        if (dataSource == null) {
            throw new RuntimeException("MySqlDistributedLock dataSource is null");
        }

        boolean tryLock = false;
        PreparedStatement ps = null;
        Connection conn = getConnection();
        try {
            tryLock = addLock(key, conn);
        } catch (Exception e) {
            tryLock = false;
        } finally {
            closeConnection(conn);
            closePreparedStatement(ps);
        }
        return tryLock;
    }

    private void closePreparedStatement( PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException("Close PreparedStatement exception " + e.getMessage());
            }
        }
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("Close mysql connection exception " + e.getMessage());
            }
        }
    }

    private boolean addLock(String key, Connection conn) throws SQLException {
        String sql = "INSERT into" + tableName + "values(?)";
        return executeSql(sql, key, conn);
    }

    private boolean releaseLock(String key, Connection conn) throws SQLException {
        String sql = "DELETE from" + this.tableName + "where lockName=?";
        try {
            executeSql(sql, key, conn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean executeSql(String sql, String params, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        ps.setString(1, params);
        ps.execute();
        conn.commit();
        return true;

    }
}
