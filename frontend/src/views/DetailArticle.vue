<template>
  <div>
    <div class="detail-article">

      <div>
        <h2>{{ communityId }}번 게시판</h2>
        <br>
      </div>

      <div class="detail-article-box">

        <div class="detail-article-box-content">
          <br>
          <h3>{{ title }}</h3>
          <br>
          <div style="display: flex; justify-content: space-between">
            <span>{{ email }} | {{ createdAt.slice(0, 19) }}</span><span>조회수 {{ hits }}</span>
          </div>
        </div>

        <hr>

        <div class="detail-article-box-content">
          <br>
          {{ content }}
        </div>

      </div>

    </div>
  </div>
</template>

<script>
  export default {
    name: 'DetailArticle',

    data: function() {
      return {
        articleId: null,
        title: '',
        content: '',
        email: '',
        createdAt: '',
        hits: '',
      }
    },
    created: function () {
      this.articleId = this.$route.params.articleId
      this.communityId = this.$route.params.communityId
      this.$axios({
        method:'get',
        url: `http://localhost:8080/api/v1/communities/${this.communityId}/articles/${this.articleId}`
      })
      .then(res => {
        console.log(res.data)
        return res.data
      })
      .then(res => {
        this.title = res.title
        this.content = res.content
        this.email = res.email
        this.createdAt = res.createdAt
        this.hits = res.hits
        return res
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
  .detail-article {
    display: flex;
    /* background-color: whitesmoke; */
    width: 100%;
    padding: 5%;
    flex-direction: column;
  }
  .detail-article-box {
    display: flex;
    /* background-color: beige; */
    border-style: solid;
    border-width: 1px;
    border-color: gray;
    flex-direction: column;
  }
  .detail-article-box-content {
    display: flex;
    /* background-color: lightpink; */
    padding: 20px;
    flex-direction: column;
  }
</style>