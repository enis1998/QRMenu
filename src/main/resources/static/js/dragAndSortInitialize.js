// dragAndSortInitialize.js
$(function() {
    // CSRF token’ı bir kere alalım
    const token = $('input[name="_csrf"]').val();

    // 1) Kategori bölümlerini Sortable ile aktif et
    $('.category-drop').each(function() {
        new Sortable(this, {
            group: 'shared',
            animation: 150,
            onAdd: evt => {
                const productId  = $(evt.item).data('prod-id');
                const categoryId = $(evt.to).data('cat-id');

                // Ajax ile tek bir atama isteği gönderiyoruz
                $.ajax({
                    url: `/admin/products/assign/${productId}/${categoryId}`,
                    method: 'POST',
                    headers: {
                        'X-CSRF-TOKEN': token
                    },
                    success: () => {
                        // (İsterseniz burada bir toast/alert gösterebilirsiniz)
                    },
                    error: (xhr, status, err) => {
                        alert('Atama sırasında bir hata oluştu.');
                    }
                });
            }
        });
    });

    // 2) Sağdaki “Atanmamış Ürünler” listesini de draggable yap
    new Sortable(document.getElementById('unassignedProducts'), {
        group: 'shared',
        animation: 150
    });
});
