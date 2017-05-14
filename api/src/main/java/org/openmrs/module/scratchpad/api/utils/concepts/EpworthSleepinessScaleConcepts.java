package org.openmrs.module.scratchpad.api.utils.concepts;

import java.util.Locale;

import org.openmrs.ConceptDescription;
import org.openmrs.ConceptName;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptNameType;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.scratchpad.api.emums.ESSAnswerType;
import org.openmrs.module.scratchpad.api.emums.ESSQuestionType;
import org.openmrs.module.scratchpad.api.utils.ConceptIds;
import org.openmrs.module.scratchpad.api.utils.ConceptUUID;

public class EpworthSleepinessScaleConcepts {
	private ConceptService conceptService = Context.getConceptService();
	private ConceptSource conceptSourceSTAR = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_STAR_UUID);
	//private ConceptSource conceptSourceCIEL = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_CIEL_UUID);
	//private ConceptSource conceptSourceSNOMEDCT = conceptService.getConceptSourceByUuid(ConceptUUID.CONCEPT_SOURCE_SNOMEDCT_UUID);
	
	public EpworthSleepinessScaleConcepts(){
		UpdateConcepts(false);
	}
	public EpworthSleepinessScaleConcepts(Boolean forceUpdate){
		UpdateConcepts(forceUpdate);
	}
	private void UpdateConcepts(boolean forceUpdate){
		UpdateESS_Ans0(forceUpdate);
		UpdateESS_Ans1(forceUpdate);
		UpdateESS_Ans2(forceUpdate);
		UpdateESS_Ans3(forceUpdate);
		UpdateESS_Q1(forceUpdate);
		UpdateESS_Q2(forceUpdate);
		UpdateESS_Q3(forceUpdate);
		UpdateESS_Q4(forceUpdate);
		UpdateESS_Q5(forceUpdate);
		UpdateESS_Q6(forceUpdate);
		UpdateESS_Q7(forceUpdate);
		UpdateESS_Q8(forceUpdate);
		UpdateESSresult(forceUpdate);
		UpdateEpworthSet(forceUpdate);
	}
	/*
	 * Answers
	 */
	private void UpdateESS_Ans0(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESS_ANSWER0_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
					ConceptUUID.CONCEPT_OMRS_CLASS_MISC);
			
			String longName = ESSAnswerType.ESSANS0.getDesc();
			String shortName = Integer.toString(ESSAnswerType.ESSANS0.getKey());
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);

			conceptBuilder.addName(
					longName, 
					"7b6e18a5-e922-401b-b966-772618afee08", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
			conceptBuilder.addName(
					shortName, 
					"79947d82-0d02-45cc-b86f-557e75abf087", 
					Locale.ENGLISH, 
					false,
					ConceptNameType.SHORT
					);	
		    conceptBuilder.setDescription(
		    		"Answer for Epworth Sleepiness Scale", 
		    		"c3678b09-fdb0-4523-8c80-fbac6fbea28e", 
		    		Locale.ENGLISH
		    		);
	        
		    conceptBuilder.addMapping(
		    		"5bffa8cc-c3e8-4351-b301-e0fba943dfb0",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Ans1(Boolean forceUpdate){
			
			boolean updateMe = forceUpdate;
			String uuid = ConceptUUID.CONCEPT_ESS_ANSWER1_UUID;
			if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
				
			if (updateMe) {	
				ConceptBuilder conceptBuilder = new ConceptBuilder(
						conceptService,
						ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
						ConceptUUID.CONCEPT_OMRS_CLASS_MISC);
				
				String longName = ESSAnswerType.ESSANS1.getDesc();
				String shortName = Integer.toString(ESSAnswerType.ESSANS1.getKey());
				
				int conceptId = ConceptIds.getConceptId(uuid);
				conceptBuilder.setUuid(uuid);
				conceptBuilder.setId(conceptId);
	
				conceptBuilder.addName(
						longName, 
						"fafd52a4-2c27-4c3c-b696-de9be6b9359d", 
						Locale.ENGLISH, 
						true,
						ConceptNameType.FULLY_SPECIFIED
						);
				conceptBuilder.addName(
						shortName, 
						"18371191-bcd3-4e73-adf0-01bcdda70a61", 
						Locale.ENGLISH, 
						false,
						ConceptNameType.SHORT
						);	
			    conceptBuilder.setDescription(
			    		"Answer for Epworth Sleepiness Scale", 
			    		"c99fe5f8-acfc-4421-a1b9-8ad6576e1ce2", 
			    		Locale.ENGLISH
			    		);
		        conceptBuilder.addMapping(
			    		"317d4aab-5350-49bf-8b1e-272a5319e2d2",
			    		conceptBuilder.SAME, 
			    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
			    		);
		        
		    	conceptBuilder.saveAndGet()	;
				
			}
		}
	private void UpdateESS_Ans2(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESS_ANSWER2_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
					ConceptUUID.CONCEPT_OMRS_CLASS_MISC);
			
			String longName = ESSAnswerType.ESSANS2.getDesc();
			String shortName = Integer.toString(ESSAnswerType.ESSANS2.getKey());
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"5244a181-2483-4f90-9ecf-d3d3f8fc3604", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
			conceptBuilder.addName(
					shortName, 
					"058479bf-c17b-4f51-9b0e-7d683e547d40", 
					Locale.ENGLISH, 
					false,
					ConceptNameType.SHORT
					);	
		    conceptBuilder.setDescription(
		    		"Answer for Epworth Sleepiness Scale", 
		    		"198c46eb-ab04-465b-88fd-a36a64b0635a", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"7edc5d0b-8814-4b1d-aca6-300739b78214",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Ans3(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESS_ANSWER3_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
					ConceptUUID.CONCEPT_OMRS_CLASS_MISC);
			
			String longName = ESSAnswerType.ESSANS3.getDesc();
			String shortName = Integer.toString(ESSAnswerType.ESSANS3.getKey());
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"5d8246f5-9ae8-428e-9dff-0b31c543c380", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
			conceptBuilder.addName(
					shortName, 
					"c3edddbf-d5ac-4fa7-99bb-9a0af7679089", 
					Locale.ENGLISH, 
					false,
					ConceptNameType.SHORT
					);	
		    conceptBuilder.setDescription(
		    		"Answer for Epworth Sleepiness Scale", 
		    		"6e2f2a06-b1b8-4328-9cc1-bdd968b38ea2", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"4c8c9885-4f8a-4419-ba66-ef16b4fb33bf",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	/*
	 * Questions
	 */
	private void UpdateESS_Q1(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ1_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q1.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);

			conceptBuilder.addName(
					longName, 
					"f0f076ae-c72e-40bb-b678-815ddeec8200", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"c954b06a-d25d-4f34-8815-ff86aaabaae5", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"9d3e5ffb-388e-4241-a072-1d393f2c5f3c",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Q2(Boolean forceUpdate){
			
			boolean updateMe = forceUpdate;
			String uuid = ConceptUUID.CONCEPT_ESSQ2_UUID;
			if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
				
			if (updateMe) {	
				ConceptBuilder conceptBuilder = new ConceptBuilder(
						conceptService,
						ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
						ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
				
				String longName = ESSQuestionType.ESS_Q2.getDesc();
				
				int conceptId = ConceptIds.getConceptId(uuid);
				conceptBuilder.setUuid(uuid);
				conceptBuilder.setId(conceptId);
	
				conceptBuilder.addName(
						longName, 
						"0a7f6d46-c3d2-467e-a3ab-a7c90654d17f", 
						Locale.ENGLISH, 
						true,
						ConceptNameType.FULLY_SPECIFIED
						);
			    conceptBuilder.setDescription(
			    		"Epworth Sleepiness Scale question", 
			    		"efac8550-d9a0-406c-a6fb-de955fb134fe", 
			    		Locale.ENGLISH
			    		);
		        conceptBuilder.addMapping(
			    		"00196ad5-676e-45d5-bcdd-056b4963418f",
			    		conceptBuilder.SAME, 
			    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
			    		);
		        
		    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
		    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
		    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
		    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
			    	
		    	conceptBuilder.saveAndGet()	;
				
			}
		}
	private void UpdateESS_Q3(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ3_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q3.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"67671325-d281-4695-821c-fbf78d48a6b6", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"064e02be-a996-454d-9b46-300eeed4da08", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"015883c7-569e-4a89-a449-06dc2220dcb1",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Q4(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ4_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q4.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"f61441e8-e3ca-41f5-a384-f6bc5d3622f7", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"1fd95147-100d-48e2-bf51-52dc3326129f", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"a3f83302-b038-4752-aaa8-a2f1394bd754",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Q5(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ5_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q5.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"878d8f63-9ada-4fdf-aa22-b69cbd631d12", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"53540207-7488-4f90-9688-7fa87c39fae8", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"b68800ac-55fe-41ca-880b-04198d480ba5",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Q6(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ6_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q6.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"ddd68cde-fc98-40ae-8c83-ec7525305bc1", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"a44dfceb-8179-40b2-8fac-54c96d95d84c", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"8afd2513-a1fa-49b1-8af2-53beeeb0d518",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Q7(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ7_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q7.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"2af6b949-954f-4fc3-8d95-4c0731343183", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"ceb15305-bf90-46de-821a-3d697d14ef8d", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"1f420f93-f2ba-4fe7-9784-c52e37faa41c",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
	private void UpdateESS_Q8(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESSQ8_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {	
			ConceptBuilder conceptBuilder = new ConceptBuilder(
					conceptService,
					ConceptUUID.CONCEPT_OMRS_DATATYPE_CODED,
					ConceptUUID.CONCEPT_OMRS_CLASS_QUESTION);
			
			String longName = ESSQuestionType.ESS_Q8.getDesc();
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setId(conceptId);
	
			conceptBuilder.addName(
					longName, 
					"07175998-8416-4359-a099-c5daffb64480", 
					Locale.ENGLISH, 
					true,
					ConceptNameType.FULLY_SPECIFIED
					);
		    conceptBuilder.setDescription(
		    		"Epworth Sleepiness Scale question", 
		    		"2cbd394f-642e-48af-9223-a2e99e49e828", 
		    		Locale.ENGLISH
		    		);
	        conceptBuilder.addMapping(
		    		"12ba6859-74dd-4640-8016-d3b8d49d1b3e",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
	        
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER0_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER1_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER2_UUID));
	    	conceptBuilder.addAnswers(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_ANSWER3_UUID));
		    	
	    	conceptBuilder.saveAndGet()	;
			
		}
	}
private void UpdateESSresult(Boolean forceUpdate){
		
		boolean updateMe = forceUpdate;
		String uuid = ConceptUUID.CONCEPT_ESS_RESULT_UUID;
		if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
			
		if (updateMe) {
			ConceptNumericBuilder conceptBuilder = new ConceptNumericBuilder(conceptService,ConceptUUID.CONCEPT_OMRS_CLASS_TEST);
			
			conceptBuilder.setUuid(uuid);
			conceptBuilder.setHiAbsolute(24.0);
			conceptBuilder.setLowAbsolute(0.0);
			conceptBuilder.setPrecise(false);
			
			String longName = "Epworth Sleepiness Scale";
			
			int conceptId = ConceptIds.getConceptId(uuid);
			conceptBuilder.setId(conceptId);
			
			ConceptName conceptName;
			conceptName = new ConceptName();
				conceptName.setUuid("24770cd0-18ca-4295-ae9a-ff86e145d1d9");
				conceptName.setName(longName);
				conceptName.setConceptNameType(ConceptNameType.FULLY_SPECIFIED);
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(true);
				conceptBuilder.add(conceptName);
			conceptName = new ConceptName();
				conceptName.setUuid("71e49a67-21ee-489b-a7bd-db99345b2fa5");
				conceptName.setName("ESS");
				conceptName.setLocale(Locale.ENGLISH);
				conceptName.setLocalePreferred(false);
				conceptBuilder.add(conceptName);
			
			ConceptDescription description;
			description = new ConceptDescription();
				description.setUuid("8ebed8a5-6df9-4afd-a718-1b770c4c1afb");
				description.setDescription("Epworth Sleepiness Score (http://epworthsleepinessscale.com) originally described in Johns MW. A new method for measuring daytime sleepiness: the Epworth Sleepiness Scale. Sleep 1991; 50-55.");
				description.setLocale(Locale.ENGLISH);
				conceptBuilder.setDescription(description);    
	        
		    conceptBuilder.addMapping(
		    		"f784a261-220b-4b0c-b95e-bab41a6c7568",
		    		conceptBuilder.SAME, 
		    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
		    		);
		    
		    conceptBuilder.saveAndGet()	;
		}
	}

	private void UpdateEpworthSet(Boolean forceUpdate){
	
	boolean updateMe = forceUpdate;
	String uuid = ConceptUUID.CONCEPT_EPWORTHSET_UUID;
	if (conceptService.getConceptNumericByUuid(uuid) == null) { updateMe=true; }
		
	if (updateMe) {	
		ConceptBuilder conceptBuilder = new ConceptBuilder(
				conceptService,
				ConceptUUID.CONCEPT_OMRS_DATATYPE_NA,
				ConceptUUID.CONCEPT_OMRS_CLASS_CONVSET);
		
		String longName = "Epworth Sleepiness Scale Questionaire";
		
		int conceptId = ConceptIds.getConceptId(uuid);
		conceptBuilder.setUuid(uuid);
		conceptBuilder.setId(conceptId);

		conceptBuilder.addName(
				longName, 
				"599e14dd-1d93-4f82-ae42-15112bc98d20", 
				Locale.ENGLISH, 
				true,
				ConceptNameType.FULLY_SPECIFIED
				);
	
	    conceptBuilder.setDescription(
	    		"Epworth Sleepiness Scale ", 
	    		"f7c70f95-597b-4f6d-bb77-0436e91ce3f0", 
	    		Locale.ENGLISH
	    		);
        conceptBuilder.addMapping(
	    		"3a2a78dc-b39e-4abb-840e-9761f95be08c",
	    		conceptBuilder.SAME, 
	    		conceptBuilder.referenceTerm(conceptSourceSTAR, Integer.toString(conceptId), longName)
	    		);
        
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ1_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ2_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ3_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ4_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ5_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ6_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ7_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESSQ8_UUID));
    	conceptBuilder.addSetMember(conceptService.getConceptByUuid(ConceptUUID.CONCEPT_ESS_RESULT_UUID));
	    	
    	conceptBuilder.saveAndGet()	;
		
	}
}
	
	
	



}
