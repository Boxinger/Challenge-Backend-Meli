package com.meli.model.pojo;


import java.util.Objects;

//Objeto Request entrada servicio TopSecretSplit
public class ServSplitRequest  {


	private String name;
    private String distance;
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	private String[] message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServSplitRequest that = (ServSplitRequest) o;
        return name.toLowerCase().equals(that.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }


}
