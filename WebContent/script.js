$(document).ready(function(){
	
	// cambio quantità carrello
	$('.quantity').on('change', function(){
    var quantity = $(this).val();
    var id = $(this).closest("td").find(".idProduct").val();
    
   $.ajax({
	  url: "Product",
	  method: "GET",
	  data: {"quantity": quantity, "id": id, "action":"cart"},
	  cache: false,
   });
   
   window.location.reload(false); 
});

$("#searchbar").keyup(function(){
	$.ajax({
		type: "POST",
		url: "Product",
		data: {"keyword": $(this).val(), "action":"searchproduct"},
		beforeSend: function(){
			$("#searchbar").css("background","#FFF");
		},
		success: function(data){
			$("#suggesstion-box").show();
			$("#suggesstion-box").html(data);
			$("#searchbar").css("background","#FFF");
		}
	});
});

function selectProduct(val){
	$("#searchbar").val(val);
	$("suggesstion-box").hide();
}


})