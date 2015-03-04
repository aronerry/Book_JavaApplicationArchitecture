package com.extensiblejava.route;

/**
 * 相较于前一版本，参数由Bill变为Routable
 *
 */
public class TypeARouter extends Router {

	public String route(Routable routable) {
		//route to location based on A routable type.
		return "A_LOCATION_";
	}

}