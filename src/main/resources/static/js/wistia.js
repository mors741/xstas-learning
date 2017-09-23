window._wapiq = window._wapiq || [];
_wapiq.push(function(W) {
    window.wistiaUploader = new W.Uploader({
        accessToken: "1c6d7aa263be410ece1e5a786e5365b2fbe808f5520af048eaa902eff0e3f9dd",
        dropIn: "wistia_uploader",
        projectId: "r4i2nh8zeh"
    });
    window.wistiaUploader.bind('uploadsuccess', function(file, media) {
        $.ajax({
            cache: false,
            type: 'POST',
            contentType : "application/json",
            dataType : 'json',
            url:   '/wistia/save-unit',
            beforeSend: function (xhr) {
                var token = $('meta[name="csrf_token"]').attr('content');
                if (token) {
                    return xhr.setRequestHeader('X-CSRF-TOKEN', token);
                }
            },
            data: JSON.stringify(1),
            success: function(data){
                alert('success');
            },
            error: function (data) {
                alert('error');
            }
        });
    });
});

var unit = {id: 2, name: "a", order: 1, content: "b", format: 1, module: 1};
console.log(unit);
$.ajax({
    cache: false,
    type: 'POST',
    contentType: "application/json",
    dataType: "json",
    url:   '/wistia/save-unit',
    data: JSON.stringify(unit),
    beforeSend: function (xhr) {
        var token = $('meta[name="csrf_token"]').attr('content');
        if (token) {
            return xhr.setRequestHeader('X-CSRF-TOKEN', token);
        }
    },
    success: function(data){
        alert('success');
    },
    error: function (data) {
        alert('error');
    }
});

