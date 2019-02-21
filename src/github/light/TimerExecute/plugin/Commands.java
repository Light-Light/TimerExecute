package github.light.TimerExecute.plugin;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import github.light.TimerExecute.api.TimerExecuteAPI;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("timer")) {
			
			if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("reload")) {
					
					if(sender.hasPermission("timer_execute.reload")) {
					
						Main.timers = Main.load("Timers.yml");
						Main.cmdStacks = Main.load("CmdStack.yml");
						Main.conds = Main.load("Conds.yml");
						Main.config = Main.load("config.yml");
						
						Main.setFromConfig();
						
						Main.api = TimerExecuteAPI.createAPI(Main.timers);
						
						sender.sendMessage(ChatColor.GREEN + "�����ļ������¼���");
					
					}else
						
						sender.sendMessage(ChatColor.RED + "�㲻��ʹ�����ָ��");
					
				}else if(args[0].equalsIgnoreCase("help")) {
					
					sender.sendMessage(ChatColor.GREEN + "���ָ�������Ϣ");
					
					sender.sendMessage(ChatColor.GREEN + "--------------------------------");
					
					sender.sendMessage(ChatColor.GREEN + "/timer reload ���������ļ�");
					
					sender.sendMessage(ChatColor.GREEN + "/timer test <��ʱ����> ����ִ��һ����ʱ��");
					
					sender.sendMessage(ChatColor.GREEN + "/timer help ��ʾ������Ϣ");
					
					sender.sendMessage(ChatColor.GREEN + "--------------------------------");
					
				}else if(args[0].equalsIgnoreCase("emp")){
					
					try{
						
						Object npc = Main.ficman.getClass().getMethod("getHandle").invoke(Main.ficman);
						
						Object handle = sender.getClass().getMethod("getHandle").invoke(sender);
						
						Object connect = handle.getClass().getField("playerConnection").get(handle);
						
						Object array = Array.newInstance(Main.entityp, 1);
						
						Array.set(array, 0, npc);
						
						Object ppopi = Main.packetpopi.getConstructor(Main.epia,Array.newInstance(Main.entityp, 0).getClass()).newInstance(Main.epia.getMethod("valueOf",String.class).invoke(null, "ADD_PLAYER"),array);
						
						Object ppones = Main.packetpones.getConstructor(Class.forName(Main.nms.getName() + ".EntityHuman")).newInstance(npc);
						
						Method send = connect.getClass().getMethod("sendPacket", Main.packet);
						
						send.invoke(connect, ppopi);
						
						send.invoke(connect, ppones);
						
					}catch(Exception e) {}
					
				}else {
					
					sender.sendMessage(ChatColor.RED + "ָ���÷�����,������/timer helpѰ�����");
					
				}
				
			}else if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("test")) {
					
					if(sender.hasPermission("timer_execute.test")) {
						
						if(Main.api.getTimer(args[1]) == null) {
							
							sender.sendMessage(ChatColor.RED + "û�������ʱ��!");
							
						}else {
							
							Main.api.getTimer(args[1]).execTimer();
							
							sender.sendMessage(ChatColor.GREEN + "��ʱ���Ѿ�����");
							
						}
					
					}else {
						
						sender.sendMessage(ChatColor.RED + "�㲻��ʹ�����ָ��");
						
					}
				
				}else {
					
					sender.sendMessage(ChatColor.RED + "ָ���÷�����,������/bkstg helpѰ�����");
					
				}
				
			}else {
				
				sender.sendMessage(ChatColor.RED + "ָ���÷�����,������/bkstg helpѰ�����");
				
			}
		
		}
		
		return true;
		
	}
	
}
