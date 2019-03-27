
	$(function() {

/*		var curpage = $('#curPaged').val() || 0;
		curpage = parseInt(curpage);
		var carNum = $('#carNumd').val() || 1;*/
		/*var carNum = 100;
		var curpage = 1;
		var carNum = Math.ceil(carNum/20);
		page({
			id:'controllerPage',
			nowNum: curpage,
			allNum: carNum
		});*/
	});


	function page(opt)
	{
		var url = 'javascript:void(0);';
		var curclass = 'curpage';//当前页样式
		var preclass = 'prePage';//上下一页样式
		var firstclass = 'firstPage';//首尾页样式	

		if(!opt){return false;}

		var obj = document.getElementById(opt.id);

		var nowNum = opt.nowNum || 1;
		var allNum = opt.allNum || 1;

		if(allNum < 2)
		{
			return;
		}


		if(nowNum >= 4 && allNum >=6)
		{
			var sy = document.createElement("a");
			sy.href = url;
			sy.innerHTML = "首页";
			sy.className = firstclass;
			sy.setAttribute("cur",1);
			obj.appendChild(sy);
		}

		if(nowNum >=2)
		{
			var syy = document.createElement("a");
			syy.href = url;
			syy.innerHTML = "上一页";
			syy.className = preclass;
			syy.setAttribute("cur",(nowNum - 1));
			obj.appendChild(syy);
		}

		if(allNum <= 5 )
		{
			for (var i = 1; i <= allNum; i++) 
			{
				var OA = document.createElement('a');
				OA.href =url;
					
					if(nowNum == i)
					{
						OA.innerHTML = i;
						OA.className = curclass;
						OA.setAttribute("cur",i);
					}
					else
					{
						OA.innerHTML = i;
						OA.setAttribute("cur",i);
					}
					obj.appendChild(OA);
			};
		}
		else
		{
			for (var i = 1; i <= 5; i++) 
			{
				var OA = document.createElement('a');	
				if(nowNum == 1 || nowNum == 2)
				{
					OA.href = url;
					OA.setAttribute("cur",i);	
					if(nowNum == i)
					{
						OA.innerHTML = i;
						OA.className = curclass;
						OA.setAttribute("cur",i);
					}
					else
					{
						OA.innerHTML = i;
						OA.setAttribute("cur",i);
					}
				}

				else if((allNum - nowNum) == 0 || (allNum - nowNum) == 1 )
				{
					OA.href = url;
					OA.setAttribute("cur",(allNum - 5 + i));

					if((allNum - nowNum) == 0  && i==5)
					{
						OA.innerHTML = (allNum - 5 + i);
						OA.className = curclass;
					}
					else if((allNum - nowNum) == 1 && i == 4)
					{
						OA.innerHTML = (allNum - 5 + i);
						OA.className = curclass;
					}
					else
					{
						OA.innerHTML = (allNum - 5 + i);
					}
				}

				else
				{
					
					OA.href = url;
					OA.setAttribute("cur",(nowNum - 3 + i));
					if(i == 3)
					{
						OA.innerHTML = (nowNum - 3 + i);
						OA.className = curclass;
					}	
					else
					{
						OA.innerHTML = (nowNum - 3 + i);

					}
				}
				
				obj.appendChild(OA);
			}
		}

		if((allNum - nowNum >= 1))
		{
			var xyy = document.createElement("a");
			xyy.href = url;
			xyy.innerHTML = "下一页";
			xyy.className = preclass;
			xyy.setAttribute("cur",(nowNum + 1));
			obj.appendChild(xyy);
		}

		if((allNum - nowNum)>= 3 && allNum >=6)
		{
			var wy = document.createElement("a");
			wy.href = url;
			wy.className = firstclass;
			wy.setAttribute("cur",allNum);
			wy.innerHTML = "尾页";
			obj.appendChild(wy);
		}
	}