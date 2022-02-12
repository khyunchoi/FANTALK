<template>
  <div style="display:flex; flex-direction: row">

    <div class="community-list-item-category">
      <br><br><br>
      <div class="community-list-item-title">
        <h2>{{ communityId }}번 게시판</h2>
      </div>
    </div>

    <div class="community-list-item">


      <br>

      <div class="community-list-item-list">

        <div class="community-list-item-list-articles" style="padding-bottom: 5px; border-style: solid; border-width: 0px 0px 2px 0px; border-color: gray;">
          <span style="width: 10%" class="community-list-item-list-articles-elements">번호</span>
          <span style="width: 50%" class="community-list-item-list-articles-elements">제목</span>
          <span style="width: 30%" class="community-list-item-list-articles-elements">작성</span>
          <span style="width: 10%" class="community-list-item-list-articles-elements">조회수</span>
        </div>

        <div class="community-list-item-list-articles" v-for="article in articles" :key="article">
          <span style="width: 10%" class="community-list-item-list-articles-elements">{{ article.articleId }}</span>
          <span style="width: 50%" class="community-list-item-list-articles-elements"><button @click="enterDetailArticle(article.articleId)">{{ article.title }}</button></span>
          <span style="width: 30%" class="community-list-item-list-articles-elements">{{ article.createdAt.slice(0,19) }}</span>
          <span style="width: 10%" class="community-list-item-list-articles-elements">{{ article.hits }}</span>
        </div>

      </div>

      <br>

      <div class="community-list-item-create">
        <button @click="enterCreateArticle()" style="padding:5px;">
          <img src="../assets/createArticleButton.png">
        </button>
      </div>

    </div>

  </div>
</template>

<script>
  const SERVER_URL = process.env.VUE_APP_API_URL
  export default {
    name: 'CommunityListItem',
    components: {
    },
    data: function() {
      return {
        communityId: null,
        communityName: null,
        articles: [],
        headers: [
          { 
            text: '', 
            value: 'articleId',
            sortable: false,
          },
          { 
            text: '제목', 
            value: 'title',
            sortable: false,
          },
          { 
            text: '작성날짜', 
            value: 'createdAt',
            sortable: false,
          },
          {
            text: '조회수',
            value: 'hits',
            sortable: false,
          },
        ],
      }
    },
    methods: {
      // goBack () {
      //   this.$router.push({ name: 'CommunityList', params: {communityId: this.communityId} })
      // },
      enterDetailArticle: function (idx) {
        console.log('aa')
        this.$router.push({name: 'DetailArticle', params:{ articleId:idx }})
        this.$router.push({name: 'DetailArticle', params:{ communityId:this.communityId }})
        DetailArticle.data.push({articleId:idx})
        DetailArticle.data.push({communityId:this.communityId})
      },
      enterCreateArticle: function () {
        this.$router.push({name: 'CreateArticle'})

      }
    },
    created: function () {
      this.communityId = this.$route.params.communityId
      console.log(this.communityId)
      this.$axios({
        method:'get',
        url: `${SERVER_URL}/api/v1/communities/${this.communityId}/articles`
      })
      .then(res => {
        return res.data
      })
      .then(res => {
        this.articles = res
        console.log(this.articles)
        return this.articles
      })
      .catch(error => {
          console.log(error)
      })
      .finally(function () {
        console.log('done')
      })
    }
  }
</script>

<style>
  .community-list-item-category {
    display: flex;
    /* background-color: beige; */
    width: 20%;
    padding: 5%;
    flex-direction: column;
  }
  .community-list-item {
    display: flex;
    /* background-color: beige; */
    width: 80%;
    padding: 5%;
    flex-direction: column;
  }
  .community-list-item-title {
    display: flex;
    /* background-color: bisque; */
    margin: 10px;
  }
  .community-list-item-list {
    display: flex;
    flex-direction: column;
  }
  .community-list-item-list-articles {
    display: flex;
    /* background-color: bisque; */
    width: 100%;
    flex-direction: row;
    justify-content: space-between;
    margin: 10px;
  }
  .community-list-item-list-articles-elements {
    display: flex;
    width: 100%;
    justify-content: center;
  }
  .community-list-item-create {
    display: flex;
    /* background-color: bisque; */
    flex-direction: row-reverse;
    margin: 10px;
  }
</style>