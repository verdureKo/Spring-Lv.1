// 사용자가 내용을 올바르게 입력하였는지 확인합니다.
function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (contents.trim().length > 140) {
        alert('공백 포함 140자 이하로 입력해주세요');
        return false;
    }
    return true;
}

// 사용자가 패스워드를 올바르게 입력하였는지 확인합니다.
function isValidPassword(password) {
    if (password == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test(password)) {
        alert('숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.');
        $('#password').val('').focus();
        return false;
    }
    var checkNumber = password.search(/[0-9]/g);
    var checkEnglish = password.search(/[a-z]/ig);
    if (checkNumber < 0 || checkEnglish < 0) {
        alert("숫자와 영문자를 혼용하여야 합니다.");
        $('#password').val('').focus();
        return false;
    }
    if (/(\w)\1\1\1/.test(password)) {
        alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
        $('#password').val('').focus();
        return false;
    }
    return false;
}

$(document).ready(function () {

    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();

});

// 게시물 전체 보여주기
function getMessages() {
    // 1. 기존 게시글 내용을 지웁니다.
    $('#cards-box').empty();
    // 2. 게시글 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/api/logs',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let message = response[i];
                let id = message['id'];
                let username = message['username'];
                let password = message['password'];
                let title = message['title'];
                let contents = message['contents'];
                let createdAt = message['createdAt'];
                let modifiedAt = message['modifiedAt'];
                addHTML(id, username, password, title, contents, createdAt, modifiedAt);
            }
        }
    })
}

// 게시물 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, username, password, title, contents, createdAt, modifiedAt) {
    let tempHtml = `<div class="card">
                <!-- date/username 영역 -->
                <div class="metadata">
                    <div class="date">
                        ${createdAt}
                    </div>
                    <div class="date" style="margin-left: 10px;">
                        ${modifiedAt}
                    </div>
                    <div id="${id}-username" class="username">
                        ${username}
                    </div>
                </div>
                <!-- contents 조회/수정 영역-->
                <div class="contents">
                    <div id="${id}-password" class="text" style="visibility: hidden">
                        ${password}
                    </div>
                    <div id="${id}-editarea-p" class="edit">
                        <textarea id="${id}-textarea-p" class="te-edit" cols="30" rows="1"></textarea>
                    </div>
                    <div id="${id}-title" class="text">
                        ${title}
                    </div>
                    <div id="${id}-editarea-t" class="edit">
                        <textarea id="${id}-textarea-t" class="te-edit" cols="30" rows="1"></textarea>
                    </div>
                    <div id="${id}-contents" class="text">
                        ${contents}
                    </div>
                    <div id="${id}-editarea-c" class="edit">
                        <textarea id="${id}-textarea-c" class="te-edit" cols="30" rows="5"></textarea>
                    </div>
                </div>
                <!-- 버튼 영역-->
                <div class="footer">
                    <img id="${id}-edit" class="icon-start-edit" src="images/edit.png" alt="" onclick="editPost('${id}')">
                    <img id="${id}-delete" class="icon-delete" src="images/delete.png" alt="" onclick="deletePost('${id}')">
                    <img id="1-confirm" class="icon-end-edit" src="/images/done.png" alt="" onclick="confirmDelete('1')">
                    <img id="${id}-submit" class="icon-end-edit" src="images/done.png" alt="" onclick="submitEdit('${id}')">
                </div>
            </div>`;
    // 1. HTML 태그를 만듭니다.
    // 2. #cards-box 에 HTML을 붙인다.
    $('#cards-box').append(tempHtml);
}

// 게시글을 생성합니다.
function writePost() {
    // 1. 작성한 게시글을 불러옵니다.
    let username = $('#username').val();
    let password = $('#password').val();
    let title = $('#title').val();
    let contents = $('#contents').val();
    let createAt = $('#createAt').val();
    let modifiedAt = $('#modifiedAt').val();

    // 2. 작성한 게시글이 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {
        'username': username,
        'password': password,
        'title': title,
        'contents': contents,
        'createAt': createAt,
        'modifiedAt': modifiedAt
    }

    // 4. POST /api/logs 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: "/api/logs",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('게시글이 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    })
}

// 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
// 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
function editPost(id) {
    showEdits(id);
    let password = $(`#${id}-password`).text().trim();
    let title = $(`#${id}-title`).text().trim();
    let contents = $(`#${id}-contents`).text().trim();

    $(`#${id}-textarea-p`).val(password);
    $(`#${id}-textarea-t`).val(title);
    $(`#${id}-textarea-c`).val(contents);

}

function showEdits(id) {

    $(`#${id}-editarea-p`).show();
    $(`#${id}-editarea-t`).show();
    $(`#${id}-editarea-c`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-title`).hide();
    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
    $(`#${id}-confirm`).hide();

}


// 게시글을 수정합니다.
function submitEdit(id) {
    // 1. 작성 대상 게시글의 username, title, password, contents 를 확인합니다.
    let username = $(`#${id}-username`).text().trim();
    let password = $(`#${id}-textarea-p`).val().trim();
    let title = $(`#${id}-textarea-t`).val().trim();
    let contents = $(`#${id}-textarea-c`).val().trim();

    // 2. 작성한 게시글이 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'username': username, 'password': password, 'title': title, 'contents': contents};

    // 4. PUT /api/logs/{id} 에 data를 전달합니다.
    $.ajax({
        type: "PUT",
        url: `/api/logs/${id}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('게시글 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}


// 삭제 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
// 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
function deletePost(id) {
    showDelete(id);
    let password = $(`#${id}-password`).text().trim();

    $(`#${id}-textarea-p`).val(password);

}

function showDelete(id) {

    $(`#${id}-editarea-p`).show();
    $(`#${id}-editarea-t`).hide();
    $(`#${id}-editarea-c`).hide();

    $(`#${id}-confirm`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-title`).hide();
    $(`#${id}-contents`).hide();

    $(`#${id}-edit`).hide();
    $(`#${id}-submit`).hide();

}


// 게시글을 삭제합니다.
function confirmDelete(id) {
    // 1. 작성 대상 게시글의 password를 확인합니다.
    let password = $(`#${id}-textarea-p`).val().trim();

    // // 2. 작성한 패스워드가 올바른지 isValidPassword 함수를 통해 확인합니다.
    // if (isValidPassword(password) == false) {
    //     return;
    // }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'password': password};

    // 3. DELETE /api/memos/{id} 에 요청해서 게시글을 삭제합니다.
    $.ajax({
        type: "DELETE",
        url: `/api/logs/${id}`,
        success: function (response) {
            alert('게시글 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
};