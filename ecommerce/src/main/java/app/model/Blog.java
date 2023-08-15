package app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "blogs")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "blog_name")
	private String blogName;

	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;
	
	@Column(name = "date_create")
	private Date dateCreate;
	public Blog() {
	}

	public Blog(Long id, String blogName, String description, String image, Date dateCreate) {
		this.id = id;
		this.blogName = blogName;
		this.description = description;
		this.image = image;
		this.dateCreate = dateCreate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@DateTimeFormat(pattern="dd-MMM-YYYY")
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Override
	public String toString() {
		return "Blog{" + "id=" + id + ", blogName='" + blogName + '\'' + ", description='" + description + ", image='"
				+ image + '\'' + ", dateCreate='" + dateCreate + '\'' + '}';
	}
}
