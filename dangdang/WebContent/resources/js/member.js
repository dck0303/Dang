
//member 관련해서 공통적으로 사용할 부분 !

	let login = () => {
		let paramObj = new Object();
		paramObj.id = id.value;
		paramObj.pw = pw.value;
		
		let headerObj = new Headers();
		headerObj.append("content-type", "application/x-www-form-urlencoded");
		
	
		fetch(urlToLogin, { /* 해당 url로 아래의 객체를 포함하여 통신요청 */
			method:"post",
			headers:headerObj,
			body:"data="+JSON.stringify(paramObj) /* json으로 문자열로 전환하여 body에 저장 */
		
		}).then(response => { /*200번대 코드가 넘어오면 ok => true */
			if(response.ok){
				return response.text(); /*controller 의 loginimpl에서 text값을 getwriter()*/	
			}
			throw new AsyncPageError(response.text()); /*200번코드가 아니면 catch 문으로 ! */
		}).then((text) => { /* 위의 text() 값이 들어온다.*/
			if(text == 'fail'){
				alert('아이디나 비밀번호를 확인하세요');
				
			}else{
				 location.href = urlToMyPage;	 
			}
		}).catch(error =>{
			error.alertMessage();
			
		});
		
	}
	
	
	

	
	
	
	let mypageError = ()=> {
		alert("로그인 후 이용하실 수 있습니다.");
		location.href="/main.do";
	}

	
	
	
	
	// Member의 id값 찾는 js
	let findUserId = () =>{ 
		let infoObj = new Object();
		infoObj.username = username.value;
		infoObj.phone = phone.value;
		
		let headerObj = new Headers();
		headerObj.append("content-type", "application/x-www-form-urlencoded");
		
		//비동기 처리해서 화면이 새로고침 되지않고 element에서만 바뀌도록 설정
		fetch(urlToFindId, { //해당 url로 객체정보 포함하여 통신요청
			method: "post",
			headers: headerObj,
			body:"userinfo=" + JSON.stringify(infoObj)
			
		}).then(response => {
			
			return response.text();
			
		}).then((text) => {
			
			if(text == 'fail'){
				alert('잘못된 회원정보를 입력하셨습니다.');
			}else{
				document.querySelector("#find_id_result").style.display = "flex";
				document.querySelector("#find_id_result").style.flexDirection = "column";
				document.querySelector('#find_id_result').innerHTML = '검색된 아이디는<h2>[' + text + ']</h2>입니다.';
				document.querySelector('#find_id_table').style.display = "none";
				
			}
		});
	};	
	
	
	
	
	
	