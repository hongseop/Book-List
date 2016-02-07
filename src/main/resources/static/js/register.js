/**
 *  회원가입 기능의 유효성 검사.
 */

function sendIt() {
	// 아이디 입력여부 검사
	if (document.form.uid.value == "") {
		alert("아이디를 입력하지 않았습니다.");
		document.form.uid.focus();
		return;

	}

	// 아이디에 공백 사용하지 않기
	if (document.form.uid.value.indexOf(" ") >= 0) {
		alert("아이디에 공백을 사용할 수 없습니다.");
		document.form.uid.focus();
		document.form.uid.select();
		return;

	}

	// 아이디 길이 체크 (6~12자)
	if (document.form.uid.value.length < 6
			|| document.form.uid.value.length > 12) {
		alert("아이디를 6~12자까지 입력해주세요.");
		document.form.uid.focus();
		document.form.uid.select();
		return;

	}

	// 비밀번호 입력여부 체크
	if (document.form.pass.value == "") {
		alert("비밀번호를 입력하지 않았습니다.");
		document.form.pass.focus();
		return;

	}

	// 비밀번호 길이 체크(4~8자 까지 허용)
	if (document.form.pass.value.length < 4
			|| document.form.pass.value.length > 8) {
		alert("비밀번호를 4~8자까지 입력해주세요.");
		document.form.pass.focus();
		document.form.pass.select();
		return;

	}
	//
	// 비밀번호와 비밀번호 확인 일치여부 체크
	if (document.form.pass.value != document.form.pass2.value) {
		alert("비밀번호가 일치하지 않습니다");
		document.form.pass.value = "";
		document.form.pass2.value = "";
		document.form.pass.focus();
		return;

	}

	// 성별 체크 유무 확인
	if (document.form.sex[0].checked == false
			&& document.form.sex[1].checked == false) {
		alert("성별을 체크해 주세요");
		return;
	}
	document.form.submit();
}