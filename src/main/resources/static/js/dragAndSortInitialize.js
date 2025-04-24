$(document).ready(function () {

    var updateOutput = function (e) {
        var list = e.length ? e : $(e.target),
            output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));
        } else {
            output.val('JSON browser support required for this demo.');
        }

        // POST işlemi burada gerçekleştirilecek
        // Serileştirilmiş verileri alıp sunucuya gönderme işlemi yapılabilir
    };

    // activate Nestable for list 1
    $('#nestable').nestable({
        group: 1
    })
        .on('change', function (e) {
            updateOutput(e);

            var token = $('input[name="_csrf"]').val(); // Token'ı al

            // Tüm güncellenmiş verileri saklayacak bir dizi oluştur
            var updatedData = [];

            // Her öğenin yeni parentId'sini güncelle
            $('.dd-item').each(function () {
                var itemId = $(this).data('id');
                var parentId = $(this).data('columns');

                // Sürüklenen öğenin parent'ı varsa ve bu parent'ın da bir parent'ı varsa, ve o parent'ın parentId'si 0 değilse
                if ($(this).parents('.dd-item').length > 1) {
                    var grandParent = $(this).parents('.dd-item').first();
                    if (grandParent.data('columns') != '00000000-0000-0000-0000-000000000000') {
                        // Bir şey yapma, devam et
                        return true; // Bu öğenin işlemlerini atla
                    }
                }

                if (parentId == '00000000-0000-0000-0000-000000000000' || parentId > '00000000-0000-0000-0000-000000000000') {
                    // Eğer üst öğe yoksa
                    var parentItem = $(this).closest('.dd-list').closest('.dd-item'); // Mevcut öğenin üst öğesini al

                    if (parentItem.length == 0) {
                        parentId = '00000000-0000-0000-0000-000000000000'; // Üst öğe yoksa parentId 0 olacak
                    } else {
                        parentId = parentItem.data('id'); // Mevcut öğenin üst öğesinin id'sini al
                    }
                }
                // Veriyi diziye ekle
                updatedData.push({
                    itemId: itemId,
                    parentId: parentId,
                    token: token
                });
            });

            // Tüm güncellenmiş verileri JSON formatına dönüştürerek gönder
            $.ajax({
                type: "POST",
                url: "/lft50sky/taxonomies/updateTaxonomies",
                contentType: "application/json",
                headers: {
                    'X-CSRF-TOKEN': token  // Header adını değiştirin
                },
                data: JSON.stringify(updatedData),
                success: function (response) {
                    // Başarılı işlem durumunda yapılacaklar
                },
                error: function (xhr, status, error) {
                    // Hata durumunda yapılacaklar
                }
            });

        });

    // output initial serialised data
    updateOutput($('#nestable').data('output', $('#nestable-output')));

    $('#nestable-menu').on('click', function (e) {
        var target = $(e.target),
            action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
    });
});