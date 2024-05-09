var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 검색 실행 함수
function searchPlaces() {
    var keyword = document.getElementById('keyword').value;
    ps.keywordSearch(keyword, placesSearchCB);
}

// 장소 검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기 위해
        // LatLngBounds 객체를 생성합니다
        var bounds = new kakao.maps.LatLngBounds();

        for (var i = 0; i < data.length; i++) {
            displayMarker(data[i]);
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    }
}

// 클릭한 장소의 정보를 표시할 <div> 요소의 ID
var placeInfoDivId = 'place-info';

// 마커를 클릭할 때 선택한 장소의 정보를 표시하는 함수
function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });

    // 마커에 클릭 이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 클릭한 위치에 대한 장소 상세 정보를 표시하거나 저장하는 코드를 작성합니다
        // 예를 들어, 클릭한 장소의 이름과 주소를 표시하는 기능을 추가합니다
        var placeName = place.place_name;
        var address = place.address_name;

        // 클릭한 장소의 정보를 <div>에 표시합니다
        var placeInfoDiv = document.getElementById(placeInfoDivId);
        placeInfoDiv.innerHTML = '<p><strong>장소 이름:</strong> ' + placeName + '</p>' +
            '<p><strong>주소:</strong> ' + address + '</p>';
    });
}

// 클라이언트 측에서 서버로 데이터를 전송하는 함수
function savePlaceToServer() {
    // 폼 데이터를 가져옵니다
    var formData = new FormData(document.getElementById('placeForm'));

    // AJAX 요청을 수행합니다
    $.ajax({
        type: 'POST',
        url: '/save-place',
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
            // 성공적으로 저장되었을 때의 처리
            console.log(response);
        },
        error: function(xhr, status, error) {
            // 오류 발생 시의 처리
            console.error(error);
        }
    });
}
