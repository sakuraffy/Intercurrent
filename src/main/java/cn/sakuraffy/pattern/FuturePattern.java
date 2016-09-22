package cn.sakuraffy.pattern;

public class FuturePattern {
	public static interface Data {
		public String getResult();
	}
	
	public static class FutureData implements Data {
		private RelData relData;
		private boolean isReady = false;
		
		public synchronized final void setRelData(RelData relData) {
			if(isReady) {
				return ;
			}
			this.relData = relData;
			isReady = true;
			notifyAll();
		}
		@Override
		public String getResult() {
			while(!isReady) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return relData.getResult();
		}
	}
	
	public static class RelData implements Data {
		private String result;
		
		public final void setResult(String result) {
			this.result = result;
		}

		public RelData(String request) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 3; i++) {
				sb.append(request);
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result = sb.toString();
		}
		
		@Override
		public String getResult() {
			return result;
		}
	}
	
	public static class Client {
		public Data request(String request) {
			final FutureData futureData = new FutureData();
			new Thread() {
				@Override
				public void run() {
					RelData relData = new RelData(request);
					futureData.setRelData(relData);
				};
			}.start();
			return futureData;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		Data data = client.request("name");
		System.out.println("request finsh");
		Thread.sleep(2000);
		System.out.println("data : " + data.getResult());
	}
}
