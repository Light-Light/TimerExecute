package com.qing_guang.TimerExecutor.plugin.main;

import com.qing_guang.TimerExecutor.plugin.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {

        if(!sender.hasPermission("timer_execute.use")){
            sender.sendMessage(ChatColor.RED + "��û��Ȩ��ʹ�ñ������ָ��!");
            return true;
        }

        if(args.length == 1){

            if(args[0].equalsIgnoreCase("help")){

                sender.sendMessage(ChatColor.GREEN + "���ָ�������Ϣ");
                sender.sendMessage(ChatColor.GREEN + "--------------------------------");
                sender.sendMessage(ChatColor.GREEN + "/timer reload ���ز��");
                sender.sendMessage(ChatColor.GREEN + "/timer test <��ʱ����> ��ʼִ��һ����ʱ���е�ָ��(enableΪtrue)");
                sender.sendMessage(ChatColor.GREEN + "/timer isr <��ʱ����> ���һ����ʱ���Ƿ���ִ��ָ��");
                sender.sendMessage(ChatColor.GREEN + "/timer stop <��ʱ����> ��һ����ʱ��ִֹͣ��ָ��(�����ʱ������ִ��ָ��)");
                sender.sendMessage(ChatColor.GREEN + "--------------------------------");

            }else if(args[0].equalsIgnoreCase("reload")) {

                if (sender.hasPermission("timer_execute.reload")) {

                    Main m = JavaPlugin.getPlugin(Main.class);
                    Bukkit.getPluginManager().disablePlugin(m);
                    Bukkit.getPluginManager().enablePlugin(m);
                    sender.sendMessage(ChatColor.GREEN + "��������������");

                } else {
                    sender.sendMessage(ChatColor.RED + "��û��Ȩ��ִ�����ָ��!");
                }

            }else{
                Bukkit.dispatchCommand(sender,"timer help");
            }

        }else if(args.length == 2){

            if(args[0].equalsIgnoreCase("test")){

                if(sender.hasPermission("timer_execute.test")){

                    if(Main.TIMERS.containsKey(args[1])){

                        sender.sendMessage(ChatColor.GREEN + "��ʱ�� " + args[1] + " �ѿ�ʼִ��");
                        Main.TIMERS.get(args[1]).execute();

                    }else{
                        sender.sendMessage(ChatColor.RED + "�˶�ʱ��������!������ڲ������ʱ���ĵ������ļ�������/timer reload�����ر����,��������ϸ��������!");
                    }

                }else {
                    sender.sendMessage(ChatColor.RED + "��û��Ȩ��ִ�����ָ��!");
                }

            }else if(args[0].equalsIgnoreCase("isr")){

                if(sender.hasPermission("timer_execute.stop")){

                    if(Main.TIMERS.containsKey(args[1])){

                        Timer timer = Main.TIMERS.get(args[1]);
                        if(timer.isRunning()){
                            sender.sendMessage(ChatColor.GREEN + "�˶�ʱ������ִ��ָ��!");
                        }else{
                            sender.sendMessage(ChatColor.GREEN + "�˶�ʱ����δ����ִ��ָ��!");
                        }

                    }else{
                        sender.sendMessage(ChatColor.RED + "�˶�ʱ��������!������ڲ������ʱ���ĵ������ļ�������/timer reload�����ر����,��������ϸ��������!");
                    }

                }else {
                    sender.sendMessage(ChatColor.RED + "��û��Ȩ��ִ�����ָ��!");
                }

            }else if(args[1].equalsIgnoreCase("stop")){

                if(sender.hasPermission("timer_execute.stop")){

                    if(Main.TIMERS.containsKey(args[1])){

                        Timer timer = Main.TIMERS.get(args[1]);
                        if(timer.isRunning()){
                            timer.stop();
                            sender.sendMessage(ChatColor.GREEN + "�˶�ʱ����ִֹͣ��ָ��!");
                        }else{
                            sender.sendMessage(ChatColor.RED + "�˶�ʱ����δ����ִ��ָ��!");
                        }

                    }else{
                        sender.sendMessage(ChatColor.RED + "�˶�ʱ��������!������ڲ������ʱ���ĵ������ļ�������/timer reload�����ر����,��������ϸ��������!");
                    }

                }else {
                    sender.sendMessage(ChatColor.RED + "��û��Ȩ��ִ�����ָ��!");
                }

            }else{
                Bukkit.dispatchCommand(sender,"timer help");
            }

        }else{
            Bukkit.dispatchCommand(sender,"timer help");
        }

        return true;

    }

}
