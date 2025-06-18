document.addEventListener('DOMContentLoaded', function() {
    const sidebarItems = document.querySelectorAll('.sidebar-item');
    const contentSections = document.querySelectorAll('.content-section');

    // 初期表示として、最初のタブ（需要予測）をアクティブにする
    const initialActiveTab = document.querySelector('.sidebar-item.active');
    const initialActiveContentId = initialActiveTab ? initialActiveTab.dataset.content : null;

    contentSections.forEach(section => {
        if (section.id === initialActiveContentId) {
            section.classList.add('active');
        } else {
            section.classList.remove('active');
        }
    });

    sidebarItems.forEach(item => {
        item.addEventListener('click', function(e) {
            e.preventDefault(); // デフォルトのリンク動作を防止

            // 現在アクティブなタブとコンテンツを非アクティブにする
            sidebarItems.forEach(i => i.classList.remove('active'));
            contentSections.forEach(s => s.classList.remove('active'));

            // クリックされたタブをアクティブにする
            this.classList.add('active');

            // 対応するコンテンツを表示する
            const targetContentId = this.dataset.content;
            const targetContent = document.getElementById(targetContentId);
            if (targetContent) {
                targetContent.classList.add('active');
            }
        });
    });
});