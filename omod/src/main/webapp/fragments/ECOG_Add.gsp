<style>

</style>
<%
	ui.includeCss("uicommons", "datetimepicker.css")
	
	def editDateFormat = new java.text.SimpleDateFormat("dd-MM-yyyy")
	def props = config.properties ?: ["uuid", "obsDatetime", "location", "valueNumeric", "comment"]
%>
<script type="text/javascript">

	jq("form").submit(function(){
		return false;
	})
</script>
	
<script>
	jq(document).ready(function() {
		subscribe("ECOGItemViewTable.selectedUUID",function(event,selectedUuid){
			jq.getJSON('${ ui.actionLink("getSelectedObs") }',
					{
						'selectedUUID': 'selectedUuid',
						'patientId': '${ config.patientId }'
					})
			  .success(function(data) {
				  
			  })
			  .error(function(xhr, status, err) {
				  alert('AJAX error ' + err);
			  })
		});
	});
</script>

<fieldset>
 	<legend>Add new ECOG Status</legend>
	<form method="post">
	
		<input type="hidden" name="user" value="${ context.authenticatedUser.userId }"/>
		<input type="text" name="uuidECOG" id="uuidECOG" value= ${ uuidNull } />
		
		<div> 
			${ ui.includeFragment("uicommons", "field/datetimepicker", [
	                    id: "dateECOG",
	                    formFieldName: "dateECOG",
	                    label:"Date:",
	                    defaultDate: new java.util.Date(),
	                    endDate: new java.util.Date(),
	                    useTime: false,
	            ])}
		</div>
		<div>
			<label for="newECOG">EGOG Status: </label>
			<select name="newECOG" id="newECOG">
				<% selectList.each { %>
					<option value = ${ Integer.toString(it.getKey()) } > ${ it.toString(false) } </option>
				<% } %>
	
			</select>
		</div>
		<div>
			<label for="commentsECOG">Comments: </label>
			<textarea name="commentsECOG" id="commentsECOG"rows = 1 cols="20"> </textarea>
		</div>
		<br/>
		<br/>
		<input type="submit" value="Save" class="confirm" />
		<input type="button" class="cancel" value = "${ ui.message('coreapps.cancel') }" onclick="javascript:window.location='${ returnUrl }'" />
			
	</form>
</fieldset>