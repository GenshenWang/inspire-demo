package com.wgs.algorithms.常见题目.停车场系统;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ParketApp {

    public void executeCommand(ParketCommand command) {
        if (command.getCommandType() == 1) {
            // 进停车场
            ParkService.getInstance().parkIn(command.getCarNo(), command.getCarType());
        } else if (command.getCommandType() == 2) {
            // 出停车场
            ParkService.getInstance().parkExit(command.getCarNo());
        }
    }

    public static void main(String[] args) {
        ParketApp app = new ParketApp();

        new Thread(() -> {
            app.executeCommand(new ParketCommand("皖A1213", 1, 1));
        }).start();

        new Thread(() -> {
            app.executeCommand(new ParketCommand("浙B2dsx13", 1, 2));
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            app.executeCommand(new ParketCommand("皖A1213", 2, 1));
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            app.executeCommand(new ParketCommand("浙B2dsx13", 2, 2));
        }).start();

    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



@Data
@AllArgsConstructor
class ParketCommand {
    private String carNo;
    private int commandType;
    private int carType;
}
