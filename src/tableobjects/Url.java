package tableobjects;
import javafx.beans.property.*;
public class Url {
	private SimpleStringProperty url;
	private SimpleStringProperty genre;
	private SimpleStringProperty descr;
	public Url(SimpleStringProperty url, SimpleStringProperty genre)
	{
		this.url=url;
		this.genre=genre;
	}
	public Url(SimpleStringProperty url, SimpleStringProperty genre, SimpleStringProperty descr)
	{
		this.url=url;
		this.genre=genre;
		this.descr=descr;
	}
	public String getUrl()
	{
		return this.url.get();
	}
	public SimpleStringProperty gUrl()
	{
		return this.url;
	}
	public void setUrl(SimpleStringProperty url)
	{
		this.url=url;
	}
	public String getGenre()
	{
		return this.genre.get();
	}
	public SimpleStringProperty gGenre()
	{
		return this.genre;
	}
	public void setGenre(SimpleStringProperty genre)
	{
		this.genre=genre;
	}
	public String getDescr()
	{
		return this.descr.get();
	}
	public SimpleStringProperty gDescr()
	{
		return this.descr;
	}
	public void setDescr(SimpleStringProperty descr)
	{
		this.descr=descr;
	}
}
