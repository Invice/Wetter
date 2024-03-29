/*
 * Represents one RSS message
 */
public class FeedMessage {

  String title;
  String description;


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

 @Override
  public String toString() {
    return "title=" + title + ",\ndescription=" + description;
  }

} 