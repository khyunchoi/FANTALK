<template>
  <div>

    <div class="detail-article-container">
      <br>
      제목 : {{ title }}
      <br><br>
      내용 :
      <br>
      {{ content }}
      <br>
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
      }
    },
    created: function () {
      this.articleId = this.$route.params.articleId
      this.communityId = this.$route.params.communityId
      console.log(this.articleId)
      console.log(this.communityId)
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
  .detail-article-container {
    width: 100%;
    display: flex;
    padding: 5%;
    flex-direction: column;
    align-items: left;
  }
</style>