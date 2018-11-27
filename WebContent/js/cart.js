
/**
 * 加入购物车
 */
function buy(goodid){
	$.post("goods_buy", {goodsid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("添加到购物车!", {time:800}, function(){
				location.reload();
			});
		}else{
			layer.msg("添加购物车失败!", {time:800}, function(){
			});
		}
//		if(data=="login"){
//			alert("请登录后购买!");
//			location.href="login.jsp";
//		}else if(data=="empty"){
//			alert("库存不足!");
//			location.reload();
//		}else{
//			alert("请求失败!");
//		}
	});
}
/**
 * 购物车减去
 */
function lessen(goodid){
	$.post("goods_lessen", {goodsid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("操作成功!", {time:800}, function(){
				location.reload();
			});
		}
//		else if(data=="login"){
//			alert("请登录后操作!");
//			location.href="login.jsp";
//		}else{
//			alert("请求失败!");
//		}
	});
}
/**
 * 购物车删除
 */
function deletes(goodid){
	$.post("goods_delete", {goodsid:goodid}, function(data){
		if(data=="ok"){
			layer.msg("删除成功!", {time:800}, function(){
				location.reload();
			});
		}
//		else if(data=="login"){
//			alert("请登录后操作!");
//			location.href="login.jsp";
//		}else{
//			alert("请求失败!");
//		}
	});
}