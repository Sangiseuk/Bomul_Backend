package com.example.bomul_backend.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	public void setLatitude(double latitude) {
		if(latitude < -90.0) {
			this.latitude = 0.0;
			return;
		}
		if(latitude > 90.0){
			this.latitude = 90.0;
			return;
		}
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		if(longitude < -180.0 || longitude > 180.0) {
			this.longitude = -180.0;
		}
		if(longitude < -180.0 || longitude > 180.0) {
			this.longitude = 180.0;
		}

		this.longitude = longitude;
	}

	public Position add(Position position) {
		return new Position(this.latitude + position.latitude, this.longitude + position.longitude);
	}

	public Position diff(Position position) {
		return new Position(position.getLatitude() - this.latitude, position.getLongitude() - this.longitude);
	}

	public double dot(Position position) {
		return position.getLatitude() * this.latitude + position.getLongitude() * this.longitude;
	}

	public double getDistance(Position position) {
		Position diffPosition = this.diff(position);
		return Math.sqrt(diffPosition.dot(diffPosition));
	}
}
