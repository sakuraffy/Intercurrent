package cn.sakuraffy.pattern;

public final class UnModifiable {
	private final int id;
	private final String name;
	
	public UnModifiable(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public final int getId() {
		return id;
	}

	public final String getName() {
		return name;
	}
}
