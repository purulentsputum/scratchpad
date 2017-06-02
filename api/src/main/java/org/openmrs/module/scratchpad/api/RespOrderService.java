package org.openmrs.module.scratchpad.api;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.TestOrder;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.scratchpad.api.model.OrderItem;

public interface RespOrderService extends OpenmrsService {

	void buildOrder(OrderItem orderItem);
	void buildOrder(Integer id, OrderItem orderItem);
	List<Order> getOrders(Patient patient);
	List<Order> getOrdersByConcept(Patient patient, Concept concept);
	List<Order> getOrdersByConcept(Patient patient, String conceptUuid);

}
