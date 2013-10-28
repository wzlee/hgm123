var log = console.log;

$(function() {
	window.firstTab = $('#firstTab');
	
	//$('[data-toggle="ajax-tab"]').ajaxTab('option', 'cacheResponse', false)
	
	$('#altLnkToTab2').click(function(e) {
		e.preventDefault();
		//$(this).ajaxTab('option', 'ajaxCompleteCallback', 'a');
		$(this).ajaxTab('show');
	})
})