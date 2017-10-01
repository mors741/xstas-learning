window._wapiq = window._wapiq || [];

$(document).ready(function () {
    var existenceVideoId = $('#existence-video-id').val();
    $('#new-video-block').hide();

    // if there is the edit page
    if(existenceVideoId){
        _wapiq.push({id: existenceVideoId, onReady: function(video) {
            video.height(200);
            video.width(400);
        }});

        _wapiq.push(function(W) {
            window.wistiaUploaderNew = new W.Uploader({
                accessToken: "1c6d7aa263be410ece1e5a786e5365b2fbe808f5520af048eaa902eff0e3f9dd",
                dropIn: "wistia_uploaderNew",
                projectId: "r4i2nh8zeh",
            });
            window.wistiaUploaderNew.bind('uploadsuccess', function(file, media) {
                $('#video').val(media.id);
                $.ajax({
                    url: 'https://api.wistia.com/v1/medias/' + existenceVideoId + '.json?api_password=1c6d7aa263be410ece1e5a786e5365b2fbe808f5520af048eaa902eff0e3f9dd',
                    type: 'DELETE'  ,
                    success: function (data) {}
                });
            });
        });
    }
    // if there is the create page
    else {
        _wapiq.push(function(W) {
            window.wistiaUploader = new W.Uploader({
                accessToken: "1c6d7aa263be410ece1e5a786e5365b2fbe808f5520af048eaa902eff0e3f9dd",
                dropIn: "wistia_uploader",
                projectId: "r4i2nh8zeh"
            });
            window.wistiaUploader.bind('uploadsuccess', function(file, media) {
                $('#video').val(media.id);
            });
        });
    }
});

$(document).on('click', '#load-new-video', function () {
    $('#new-video-block').show();
    $(this).prop('disabled', true);
});