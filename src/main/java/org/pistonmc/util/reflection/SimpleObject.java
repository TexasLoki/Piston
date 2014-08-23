package org.pistonmc.util.reflection;

public class SimpleObject {

	private Class<?> type;
	private Object object;

    public SimpleObject(Object object) {
        this(object, object.getClass());
    }

    public SimpleObject(Object object, Class<?> type) {
		this.object = object;
		this.type = type;
	}

	public Object getObject() {
		return object;
	}

	public SimpleMethod method(String name, Class<?>... params) {
		try {
			return new SimpleMethod(this, type.getDeclaredMethod(name, params));
		} catch(NoSuchMethodException e) {
			return null;
		}
	}

	public SimpleField field(String name) {
		try {
			return new SimpleField(this, type.getDeclaredField(name));
		} catch(NoSuchFieldException e) {
			return null;
		}
	}

}
