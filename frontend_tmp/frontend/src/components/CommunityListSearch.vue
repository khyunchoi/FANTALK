<template>
  <div> 

    <div class="community-list-header">
      <div class="search-bar-menu">
        검색창 / 검색버튼 / 등록버튼
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'CommunityListSearch',
  data: function () {
  },
}
</script>

<style>
  .community-list-header {
    width: 100%;
    display: flex;
    padding: 10%;
    flex-direction: column;
    align-items: center;
  }

  .search-bar-menu {
    width: 100%;
    display: flex;
    padding: 10%;
    flex-direction: column;
    align-items: center;
    color: #7743b3;
    background-color: #240361;
    font-size: 1.5em;
  }


</style>