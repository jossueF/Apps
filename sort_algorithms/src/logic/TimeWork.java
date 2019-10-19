package logic;

public class TimeWork {

	public long getLastTime() {
		return System.nanoTime();
	}
	
	public String getTimeNow(long timeOne, long timeTwo) {
		long timeNow = (timeTwo - timeOne);
		long timeSec = timeNow / 1000000000;
		long timeMS = timeNow / 1000000;
		
		if(timeSec >= 1) {
			return String.format("%d sec", timeSec);
		}
		else if(timeMS >= 1) {
			return String.format("%d ms", timeMS);
		}
		else {
			return String.format("%d ns", timeNow);
		}
	}
}
