package application;
import java.io.Serializable;

public class CoreData implements Serializable{
	private String programHome;//core data
	private String xampp;//interface
	private String wamp;//interface
	//process execution path variables
	private String service_apache_start;
	private String service_apache_stop;
	private String service_mysql_start;
	private String service_mysql_stop;
	private String db_start;
	private String db_stop;
	
	public CoreData()
	{
		programHome=xampp=wamp=service_apache_start=null;
		service_apache_stop=service_mysql_start=service_mysql_stop=db_start=db_stop=null;
	}
	//getters, setters
	public final String getProgramHome() {
		return programHome;
	}
	public final String getXampp() {
		return xampp;
	}
	public final String getWamp() {
		return wamp;
	}
	public final String getService_apache_start() {
		return service_apache_start;
	}
	public final String getService_apache_stop() {
		return service_apache_stop;
	}
	public final String getService_msql_start() {
		return service_mysql_start;
	}
	public final String getService_msql_stop() {
		return service_mysql_stop;
	}
	public final String getDb_start() {
		return db_start;
	}
	public final String getDb_stop() {
		return db_stop;
	}
	public final void setProgramHome(String programHome) {
		this.programHome = programHome;
	}
	public final void setXampp(String xampp) {
		this.xampp = xampp;
	}
	public final void setWamp(String wamp) {
		this.wamp = wamp;
	}
	public final void setService_apache_start(String service_apache_start) {
		this.service_apache_start = service_apache_start;
	}
	public final void setService_apache_stop(String service_apache_stop) {
		this.service_apache_stop = service_apache_stop;
	}
	public final void setService_msql_start(String service_msql_start) {
		this.service_mysql_start = service_msql_start;
	}
	public final void setService_msql_stop(String service_msql_stop) {
		this.service_mysql_stop = service_msql_stop;
	}
	public final void setDb_start(String db_start) {
		this.db_start = db_start;
	}
	public final void setDb_stop(String db_stop) {
		this.db_stop = db_stop;
	}
	
	
}
