$(function(){
    //상태 객체
    var state = {
        bookTitleValid : false,
        bookAuthorValid : true,//선택항목
        bookPublisherValid : true,//선택항목
        bookPublicationDateValid : true,//선택항목
        bookPriceValid : false,
        bookPageCountValid : false,
        bookGenreValid : false,
        ok : function() {
            return Object.values(this)//이 객체의 모든 이름에 대한 값을 반환
                    .filter(v => typeof v === "boolean")//)//boolean값만 추출해서
                    .every(v => v === true);//모두 true인지 확인해서 반환해라!
        }
    };

    //항목 검사
    $("[name=bookTitle]").on("blur", function(){
        var valid = $(this).val().length > 0;
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.bookTitleValid = valid;
    });
    $("[name=bookAuthor]").on("blur", function(){
        var regex = /^[^!@#$]+$/;
        var valid = $(this).val().length == 0 || regex.test($(this).val());//없거나 형식에 맞거나
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.bookAuthorValid = valid;
    });
    $("[name=bookPublisher]").on("blur", function(){
        $(this).addClass("success");
        state.bookPublisherValid = true;
    });
    $("[name=bookPublicationDate]").on("blur", function(){
        var regex = /^([0-9]{4})-(((02)-(0[1-9]|1[0-9]|2[0-9]))|((0[469]|11)-(0[1-9]|1[0-9]|2[0-9]|30))|((0[13578]|1[02])-(0[1-9]|1[0-9]|2[0-9]|3[01])))$/;
        var valid = regex.test($(this).val());
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.bookPublicationDateValid = valid;
    });
    $("[name=bookPrice]").on("blur", function(){
        var price = parseInt($(this).val());
        var valid = price >= 0 && price <= 10000000000;
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.bookPriceValid = valid;
    });
    $("[name=bookPageCount]").on("blur", function(){
        var pages = parseInt($(this).val());
        var valid = pages > 0;
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.bookPageCountValid = valid;
    });
    $("[name=bookGenre]").on("input", function(){
        var regex = /^(판타지|교양|소설|역사|과학|추리소설|자기계발|수험서)$/;
        var valid = regex.test($(this).val());
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.bookGenreValid = valid;
    });

    //숫자 입력창 처리
    $("[inputmode=numeric]").on("input", function(){
        var originValue = $(this).val();
        var replaceValue = originValue.replace(/[^0-9]/g, "");
        $(this).val(parseInt(replaceValue || 0));//replaceValue가 빈칸이면 0으로 대체
    });
    
    //폼 검사
    $(".form-check").on("submit", function(){
        //화면 처리(이벤트 트리거)
        $(this).find("select[name]").trigger("input");
        $(this).find("input[name], textarea[name]").trigger("blur");

        return state.ok();//state.ok() 상태에 따라 전송해!
    });
});