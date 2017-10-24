$(document).ready(function() {
	$("#login").click(function(event) {
		$.ajax({
		url: 'rsa/getk',
		type: 'POST',
		// dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
		// data: {param1: 'value1'},
	})
	.done(function(e) {
		var v = {
			userName:$("#userName").val(),
			pwd:$("#pwd").val(),
		}
		var val = JSON.stringify(v);
		var encrypt = new JSEncrypt();
		encrypt.setPublicKey(e);
		var encrypted = encrypt.encrypt(val);
		// console.log(encrypted)
		$.ajax({
			url: 'author/login',
			type: 'POST',
			// dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
			data: {k: encrypted},
		})
		.done(function(e) {
			console.log("success");
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	})
	.fail(function() {
		
	})
	.always(function() {
		
	});
	});

	
});