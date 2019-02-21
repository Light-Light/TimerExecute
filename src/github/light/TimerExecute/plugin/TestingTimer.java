package github.light.TimerExecute.plugin;

/**
 * ���׼�ʱ����,��������һ���߳�
 * @author Light
 *
 */
public abstract class TestingTimer{
	
	/**
	 * ͬ����Դ��ʱ������
	 * @param resource ͬ������Դ
	 */
	public TestingTimer(Object resource) {
		
		this.append = true;
		
		this.resource = resource;
		
	}
	
	/**
	 * Ĭ�ϼ�ʱ������
	 */
	public TestingTimer() {}

	//��ʱ���Ƿ�ֹͣ
	private boolean flag = true;
	
	//���߳�ͬ�������첽
	private boolean append = false;
	
	//ͬ������Դ
	private Object resource;
	
	/**
	 * ��ʱ����������
	 */
	protected abstract void runMethod() throws Exception;
	
	/**
	 * ����ʱ�ô˼�ʱ��ֹͣ����(��ȫ)
	 */
	public void stop() {
		
		flag = false;
		
	}
	
	/**
	 * ��������ʱ���ǲ��Ǳ���ͣ
	 * @return 
	 */
	public boolean isStop() {
		
		return !flag;
		
	}
	
	/**
	 * ������д��runMethod����
	 * @param delay ��ʱ������֮ǰ����ʱ
	 * @param num runMethod��Ҫִ�ж��ٱ�,����-1�������ѭ��
	 * @param interval ����һ��֮��ļ��(�������ִ��һ��)
	 */
	public void start(long delay,int num,long interval){
		
		new Thread(new Thread01("��ʱ��") {
			
			public void run() {
				
				int num1 = num;
				
				try {
				
					Thread.sleep(delay);
				
					if(num == -1)
						
						while(flag) {
							
							if(append && (resource != null)) {
							
								synchronized (resource) {
								
									runMethod();
									
								}
							
							}else
								
								runMethod();
						
							Thread.sleep(interval);
						
						}
					
					while((num1 > 0) && flag){
						
						runMethod();
					
						num1--;
					
						Thread.sleep(interval);
						
					}
				
				} catch (Exception e) {
					
					e.printStackTrace();
				
				}
				
			}
			
		}).start();
		
	}
	
	//�������ٵ��߳��ڲ���
	private class Thread01 extends Thread{
		
		private Thread01(String name){
			
			super(name);
			
		}
		
	}
	
}
