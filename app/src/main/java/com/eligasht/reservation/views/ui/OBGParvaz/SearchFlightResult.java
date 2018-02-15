package com.eligasht.reservation.views.ui.OBGParvaz;

import java.util.List;

public class SearchFlightResult {
	
private Comment Comments ;

private List<Errore> Errors ;

private List<Flight> Flights ;

private int ResultCount ;

private String ResultUniqID ;

public Comment getComments() {
	return Comments;
}

public void setComments(Comment comments) {
	Comments = comments;
}

public List<Errore> getErrors() {
	return Errors;
}

public void setErrors(List<Errore> errors) {
	Errors = errors;
}

public List<Flight> getFlights() {
	return Flights;
}

public void setFlights(List<Flight> flights) {
	Flights = flights;
}

public int getResultCount() {
	return ResultCount;
}

public void setResultCount(int resultCount) {
	ResultCount = resultCount;
}

public String getResultUniqID() {
	return ResultUniqID;
}

public void setResultUniqID(String resultUniqID) {
	ResultUniqID = resultUniqID;
}

}
