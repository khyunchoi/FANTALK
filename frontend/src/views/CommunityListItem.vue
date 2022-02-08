<template>
  <div>

    <div class="community-list-articles-container">
      <h1>{{ communityId }}번 게시판</h1>
      <h3>커뮤니티 게시글 목록</h3>
      <hr>

      <button
        v-for="article in articles"
        style="padding: 3%; border-style: solid; border-width: 0px 0px 3px 0px"
        @click="enterDetailArticle(article.articleId)"
      >
        {{ article.articleId }} | {{ article.title }}
      </button>

    </div>


    

  </div>
</template>

<script>
  export default {
    name: 'CommunityListItem',
    components: {
    },
    data: function() {
      return {
        communityId: null,
        communityName: null,
        articles: {}
      }
    },
    methods: {
      // goBack () {
      //   this.$router.push({ name: 'CommunityList', params: {communityId: this.communityId} })
      // },
      enterDetailArticle: function (idx) {
        this.$router.push({name: 'DetailArticle', params:{ articleId:idx }})
        this.$router.push({name: 'DetailArticle', params:{ communityId:this.communityId }})
        DetailArticle.data.push({articleId:idx})
        DetailArticle.data.push({communityId:this.communityId})
      }
    },
    created: function () {
      this.communityId = this.$route.params.communityId
      console.log(this.communityId)
      this.$axios({
        method:'get',
        url: `http://localhost:8080/api/v1/communities/${this.communityId}/articles`
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
  .community-list-articles-container {
    width: 100%;
    display: flex;
    padding: 5%;
    flex-direction: column;
    align-items: left;
  }
</style>