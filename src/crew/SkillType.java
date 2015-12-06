package crew;

public class SkillType {

	private int id;
	private String Name;
	
	public SkillType(int id,String Name){
		this.id=id;
		this.Name=Name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "SkillType id=" + id + ", Name=" + Name;
	}
	
}
