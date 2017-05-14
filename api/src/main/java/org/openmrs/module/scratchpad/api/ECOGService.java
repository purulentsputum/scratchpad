/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.scratchpad.api;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.api.OrderContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ECOGService extends OpenmrsService{
	public List<Obs> getObs();
	public List<Obs> getObs(Patient patient);
	public void saveObs(Obs obs);
	public Obs getObs(String uuid);
	public Concept getECOGConcept();
	public Order getOrder(String uuid);
	public void saveOrder(Order order,OrderContext orderContext);
	public List<Order> getActiveOrdersByPatient(Patient patient);
	public List<Order> getAllOrders(Patient patient);
	public void discontinueOrder(Order order);
	public List<Obs> getObsSet(String uuid);

}
