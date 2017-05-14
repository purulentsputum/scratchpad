package org.openmrs.module.scratchpad.api.utils;

import java.util.Comparator;

import org.openmrs.Obs;

public class ObsByDateDescendingComparator implements Comparator<Obs> {


	@Override
	public int compare(Obs obs1, Obs obs2) {
		if (obs1 == null || obs1.getObsDatetime() == null) {
            return 1;
        }

        if (obs2 == null || obs2.getObsDatetime() == null)  {
            return -1;
        }
     // note that we are sorting so that most recent date is first
        return obs2.getObsDatetime().compareTo(obs1.getObsDatetime());
	}

}
