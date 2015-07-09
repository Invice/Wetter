

public class Wetter {
	
	private double windspeed = 0;
	private double winddir = 0;
	
	public boolean alarm(){
		return windspeed>=5 && calcDir(winddir).equals("NE");
	}
	
	public String calcDir(double arg){
	
		double s = 22.5/2;
		
		String temp = "";
		if (arg>360-s){
			temp = "N";
		}
		if (arg>337.5-s){
			temp = "NNW";
		} else if (arg>315-s){
			temp = "NW";
		} else if (arg>292.5-s){
			temp = "WNW";
		} else if (arg>270-s){
			temp = "W";
		} else if (arg>247.5-s){
			temp = "WSW";
		} else if (arg>225-s){
			temp = "SW";
		} else if (arg>202.5-s){
			temp = "SSW";
		} else if (arg>180-s){
			temp = "S";
		} else if (arg>157.5-s){
			temp = "SSE";
		} else if (arg>135-s){
			temp = "SE";
		} else if (arg>112.5-s){
			temp = "ESE";
		} else if (arg>90-s){
			temp = "E";
		} else if (arg>67.5-s){
			temp = "ENE";
		} else if (arg>45-s){
			temp = "NE";
		} else if (arg>22.5-s){
			temp = "NNE";
		} else if (arg> 0-s) { 
			temp = "N";
		}
		
		return temp;
		
		
	}
	

	
	public double getWindspeed(){
	    RSSFeedParser parser = new RSSFeedParser("http://www.geomar.de/service/wetter/feed/");
	    Feed feed = parser.readFeed();
		
		String[]  baum =  feed.getMessages().get(0).getDescription().split("<br />");
				
		String ast = baum[3].trim();
		
		String speed = "";
		
		char[] p = new char[5]; 
		ast.getChars(21, 25, p, 0);
		
		for(int i=0;i<5;i++){
			speed = speed + p[i];
		}
		
		speed = speed.replaceAll(",", ".");
		
		double blatt = Double.parseDouble(speed);
		
		this.windspeed = blatt;
		
		return this.windspeed;
		
	}
	
	public double getDirection(){
	    RSSFeedParser parser = new RSSFeedParser("http://www.geomar.de/service/wetter/feed/");
	    Feed feed = parser.readFeed();
		
		String[]  baum =  feed.getMessages().get(0).getDescription().split("<br />");
		
		String ast = baum[2].trim();
		
		String speed = "";
		
		char[] p = new char[3]; 
		ast.getChars(14, 17, p, 0);
		
		for(int i=0;i<3;i++){
			speed = speed + p[i];
		}
		
		speed = speed.replaceAll(",", ".");
		double blatt = Double.parseDouble(speed);
		
		this.winddir = blatt;
		
		return this.winddir;
	}
	
	
	
	public String getTime(){
	    RSSFeedParser parser = new RSSFeedParser("http://www.geomar.de/service/wetter/feed/");
	    Feed feed = parser.readFeed();
		
		String[] tmp = feed.getMessages().get(0).getTitle().split("\\s");
    	return tmp[2];
	}
	
	

	public static void main(String[] args) {
		Wetter wetter = new Wetter();
	  
		
	    System.out.println("Uhrzeit der Daten: "+ wetter.getTime());
	    System.out.println("Windgeschwindigkeit: " + wetter.getWindspeed() + " m/s") ;
	    System.out.println("Windrichtung: " + wetter.getDirection() + "° " +
	    		                              wetter.calcDir(wetter.getDirection()));
	    System.out.println(wetter.alarm());

//	    for(int i=0; i<17; i++){
//	    	System.out.println(i*22.5);
//	    }
//		double nne = 22.5;
//		double ene = 67.5;
	  }
	
}
