//강좌 등록화면 검사 코드
$(function(){
    //상태 객체
    var state = {
        lectureTitleValid : false,
        lectureCategoryValid : false,
        lectureDurationValid : false,
        lecturePriceValid : false,
        lectureTypeValid : false,
        ok : function() {
            return Object.values(this)//이 객체의 모든 이름에 대한 값을 반환
                    .filter(v => typeof v === "boolean")//)//boolean값만 추출해서
                    .every(v => v === true);//모두 true인지 확인해서 반환해라!
        }
    };

    //항목 검사
    $("[name=lectureTitle]").on("blur", function(){
        var valid = $(this).val().length > 0;
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.lectureTitleValid = valid;
    });
    $("[name=lectureCategory]").on("input", function(){
        var regex = /^(이론|실습|시험)$/;
        var valid = regex.test($(this).val());
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.lectureCategoryValid = valid;
    });
    $("[name=lectureDuration]").on("blur", function(){
        var duration = parseInt($(this).val());//NaN 나올 수 없음
        var valid = duration <= 300 && duration % 30 == 0;
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.lectureDurationValid = valid;//state에 결과 저장
    });
    $("[name=lecturePrice]").on("blur", function(){
        var price = parseInt($(this).val());
        var valid = price >= 0 && price <= 1000000000;
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.lecturePriceValid = valid;//state에 결과 저장
    });
    $("[name=lectureType]").on("input", function(){
        var regex = /^(온라인|오프라인|혼합)$/;
        var valid = regex.test($(this).val());
        $(this).removeClass("success fail").addClass(valid ? "success" : "fail");
        state.lectureTypeValid = valid;//state에 결과 저장
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