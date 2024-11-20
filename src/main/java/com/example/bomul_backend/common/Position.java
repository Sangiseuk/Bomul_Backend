package com.example.bomul_backend.common;

public class Position {
	double latitude;
	double longitude;
	
	public Position() {
		this.latitude = 0.0;
		this.longitude = 0.0;
	}
	
	public Position(Position position) {
		this.latitude = position.latitude;
		this.longitude = position.longitude;
	}
	
	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
}
