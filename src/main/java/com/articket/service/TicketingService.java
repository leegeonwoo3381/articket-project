package com.articket.service;

import java.util.List;
import java.util.Map;

public interface TicketingService {
	public int payment(Map<String, Object> param);

	public List<Map<String, Object>> ticketList(int id);

}
