


<script type="text/javascript">
	
	
	function essByQuestionsClicked(essRadio){
		jq("#essByQuestions").val(essRadio.value);
		if (essRadio.value=="true"){
			jq('#ESSbyTotal').hide();
			jq('#ESSbyItem').show();
		}else{
			jq('#ESSbyTotal').show();
			jq('#ESSbyItem').hide();
		}
	}
	
	jq(function(){
		jq("#date").datepicker({
			dateFormat:"${dateFormat}",
			defaultDate:0,
			maxDate:0,
			showOtherMonths:true,
            selectOtherMonths: true
		});
		jq("#date").datepicker("setDate", jq.datepicker.formatDate("${dateFormat}", new Date()));
	});
	

	function populateExistingObs(obsId) {
		newObservation();
		jq.getJSON('${ ui.actionLink("getObsGroup") }',
				            {
				              'obsId': obsId
				            })
				        .success(function(data) {
							jq("#date").datepicker("setDate", jq.datepicker.formatDate("${dateFormat}", new Date(data.date)));							
							jq("#location option").each(function(){if(jq(this).text() == data.location) {jq(this).attr('selected', 'selected');}});   //val(data.location);
							jq("#labNumber").val(data.labNumber);
							jq("#obsGroupUUID").val(data.obsGroupUUID);
							jq("#obsId").val(data.obsId);
							jq("#ESSresult").val(data.ESSresult);
							jq("#comments").val(data.comments);
							
							document.forms["ESSdata"]["Q1"].value=data.Q1;
							document.forms["ESSdata"]["Q2"].value=data.Q2;
							document.forms["ESSdata"]["Q3"].value=data.Q3;
							document.forms["ESSdata"]["Q4"].value=data.Q4;
							document.forms["ESSdata"]["Q5"].value=data.Q5;
							document.forms["ESSdata"]["Q6"].value=data.Q6;
							document.forms["ESSdata"]["Q7"].value=data.Q7;
							document.forms["ESSdata"]["Q8"].value=data.Q8;
							validateESSQuestions();
							
							//{   "encounter","visit" };
				            
				        })
				        .error(function(xhr, status, err) {
				            alert('AJAX error ' + err);
				        })
		
	}
	
	function clearQuestions() {
		document.forms["ESSdata"]["Q1"].value="-1";
		document.forms["ESSdata"]["Q2"].value="-1";
		document.forms["ESSdata"]["Q3"].value="-1";
		document.forms["ESSdata"]["Q4"].value="-1";
		document.forms["ESSdata"]["Q5"].value="-1";
		document.forms["ESSdata"]["Q6"].value="-1";
		document.forms["ESSdata"]["Q7"].value="-1";
		document.forms["ESSdata"]["Q8"].value="-1";
	}
	
	function setDefaults(){
		jq("#date").datepicker("setDate", jq.datepicker.formatDate("${dateFormat}", new Date()));							
		jq("#location option").each(function(){
			if(jq(this).text() == "${sessionContext.sessionLocation.getName()}") {jq(this).attr('selected', 'selected');}
		});   
		jq("#labNumber").val("${ uuidNew }");
		jq("#obsId").val('0');
		jq("#ESSresult").val("");
		jq("#comments").val("");
		jq("#changeReason").val("");
		clearQuestions();
		validateESSQuestions();
	}
	
	function obsIdChange() {
		if (jq("#obsId").val()>0) {
			jq("#changeReasonRow").show();
		} else {
			jq("#changeReasonRow").hide();
		}
	}

	function calculateESS() {
		var allGood = "good";
		var essResult = 0;
		
		var question = "Q1";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q2";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q3";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q4";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q5";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q6";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q7";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		var question = "Q8";
		var answer=document.forms["ESSdata"][question].value;
		if ((!(answer < 0))&&(!(answer > 3))&&(!(answer == ""))) {
			essResult=essResult+parseInt(answer);
			document.forms["ESSdata"][question].style.color="black";
		} else {
			allGood = "bad";
			document.forms["ESSdata"][question].style.color="red";
		}
		
		if (allGood=="good") {
			return essResult;
		} else {
			return -1;
		}
	};

	function validateESSQuestions() {
		var x= calculateESS();
			if ((!(x<0))&&(!(x>24))) {
				jq("#ESSresultCalc").html(x);
			} else {
				jq("#ESSresultCalc").html("incomplete");
			}
	}

	function validateForm() {
		var x = calculateESS();
		var y = document.forms["ESSdata"]["ESSresult"].value;
		var xGood = false;
		var yGood = false;
		
		if ((!(x<0))&&(!(x>24))) {
			xGood=true;
		} 
		if ((!(y<0))&&(!(y>24))) {
			yGood=true;
		}
		if (xGood || yGood){
			return true;
		}else{
			return false;
		}
	}

	function newObservation() {
		//setDefaults();
		jq("#addObservation").show();
		setDefaults();
	}


	jq.subscribe("scratchpad/ESSViewFragment/selectedItem",function(data) {
		populateExistingObs(data);
	});
</script>

<div>
	<button type="button" id="createNewObservation" onclick="newObservation()">create new result new</button></br>
	<div id="addObservation" style="display:none">
	
		<form name="ESSdata" method="post" onsubmit="return validateForm()">
		<div id="demographics">
			<fieldset>
				<table>
					<tr>
						<td>
							<label for="date">Date: </label>
							<input type="text" id="date" name="date"  />
						</td>
						<td>
							${ ui.includeFragment("uicommons", 
									  "field/location", 
									  [
				                          "id": "location",
				                          "formFieldName": "location",
				                          "label": "Location: ",
				                          "initialValue": sessionContext.sessionLocation
				                       ])}
		
						</td>
						<td>
							<label for="labNumber">Test/Lab Number: </label>
							<input type="text" id="labNumber" name="labNumber" value='${ uuidNew }' >
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<label for'comments'>Comments: </label>
							<input type='text' id='comments' name='comments' placeholder='Add comments here' data-clear-btn="true">
						</td>
					</tr>
				</table>
				<table>
				<tr id="changeReasonRow">
					<td>
						<label for'changeReason'>Reason for editing the result: </label>
						<input type='text' id='changeReason' name='changeReason' placeholder='Enter reason for editing results' data-clear-btn="true">
					</td>
				</tr>
			</table>
			</fieldset>
		</div>
		</br>
		
			<label for="toggleInputButton">Change Input Method: </label>
			<input type="button" onclick='buttonClicked()' id="toggleInputButton" name="toggleInputButton"  value='Result only'>
			<br>
			  <input type="radio" name="essByQuestions" value="true" onclick="essByQuestionsClicked(this)" checked> Individual questions<br>
			  <input type="radio" name="essByQuestions" value="false"onclick="essByQuestionsClicked(this)"> Result only<br>
			  
		</br>
		<br>
		<div>
			<input type='text' id='obsGroupUUID' name='obsGroupUUID' value=' ${ uuidNull }' />
			<input type="text" id="obsId" name="obsId" value="0" onchange="obsIdChange()"/>
			<input type="text" id="essByQuestions" name="essByQuestions" value="true" style="display:none"/>
		</div>
		<div id="ESSbyTotal" style="display:none">
			<fieldset>
				<label for="ESSresult">Epworth Sleepiness Score result: </label>
				<input type="number" name="ESSresult" id="ESSresult" min="1" max="24" value="" >
			</fieldset>
		</div>
		
		<div id="ESSbyItem">
			<fieldset>
				<legend>
					Enter new Epworth Sleepiness Score
				</legend>
				<table id="ESStable">
					<tr>
						<th>ESS Question</th>
						<th>ESS Answer</th>
					</tr>
					<% questionList.each { questionItem -> %>
						<tr>
							<td>
								${ questionItem.desc }
							</td>
							<td>
								<select id="Q${ questionItem.key }" name="Q${ questionItem.key }" size="1" onChange="validateESSQuestions()">
									<option value="-1" selected>not documented</option>
									<% answerList.each {answerlist -> %>
										<option value=${ answerlist.key }>${ answerlist.desc }</option>
									<% } %>
								</select>	
							</td>
						</tr>
					 <% } %>
					 <tr>
					 	<td>Epworth Sleepiness Scale result</td>
					 	<td><label id='ESSresultCalc' name='ESSresultCalc'>pending</label></td>
				 </table>
				 
			 </fieldset>
		 </div>
		 
		 
			</br>
			
				
			<input type="submit" value="Save" class="confirm" />
			<input type="button" class="cancel" value = "${ ui.message('coreapps.cancel') }" onclick="javascript:window.location='${ returnUrl }'" />
						
			
		</form>
	</div>
</div>
