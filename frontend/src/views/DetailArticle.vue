<template>
  <div class="main-container">

    <div style="padding: 6% 0 2% 2%;">
      <h2 style="padding: 0 0 0 3%;">{{communityName}} 팬 커뮤니티</h2>
      <img src="../assets/boardsidebar.png" style="padding: 10% 0 0 0;" alt="ArticleList" @click="moveCommunityListItem">
    </div>

    <div class="article-comment-container">
      <div class="article-container">
        <div id="title">
          {{ title }}
        </div>

        <div id="etc-container">
          <div>{{ email }} | {{ createdAt }}</div>
          <div>조회수 {{ hits }}</div>
        </div>

        <div id="content">
          <p>{{ content }}</p>
        </div>
      </div>

    <v-btn @click="updateArticle()" id="update-btn">수정</v-btn>

    <div class="comment-container">
      <div>
        총 {{ commentCnt }}건의 댓글이 있습니다.
      </div>

      <div>
        <v-text-field
          v-model="commentContent"
          label="댓글을 입력하세요."
          :rules="contentRules"
          solo
          required
        ></v-text-field>
        <v-btn @click="submitComment()" style="background-color: #797BF8; color: #FFFFFF; margin: 0 10px;">등록</v-btn>
      </div>

      <div v-for="comment in commentList" :key="comment">
        {{ comment.content }}
      </div>
    </div>
    </div>
  </div>
</template>

<script>
  const SERVER_URL = process.env.VUE_APP_API_URL
  export default {
    name: 'DetailArticle',

    data: function() {
      return {
        communityName: '',
        articleId: null,
        title: '',
        content: '',
        email: '',
        createdAt: '',
        hits: '',
        commentList: [],
        communityId: null,
        commentCnt: 0,
        commentContent: '',
        contentRules: [
          v => (v && v.length < 1) || '댓글은 1자 이상이어야 합니다.'
        ],
      }
    },
    methods: {
      moveCommunityListItem() {
        this.$router.push({name: 'CommunityListItem', params: {communityId: this.communityId}})
      },
      setToken () {
        const token = localStorage.getItem('jwt')
        const config = {
          Authorization: `Bearer ${token}`
        }
        return config
      },
      submitComment() {
        if (this.commentContent.length >= 1) {
          const commentItem = {
            content: this.commentContent
          }
          this.$axios({
            method: 'post',
            url: `${SERVER_URL}/api/v1/articles/${this.articleId}/comments`,
            headers: this.setToken(),
            data: commentItem,
          })
          .then(res => {
            console.log(res)
            location.reload()
          })
          .catch(err => {
            console.log(err)
          })
        } else {
          alert('댓글은 1자 이상 작성해야 합니다.')
        }
      }
    },
    created: function () {
      this.articleId = this.$route.params.articleId
      this.communityId = this.$route.params.communityId
      this.$axios({
        method:'get',
        url: `${SERVER_URL}/api/v1/communities/${this.communityId}/articles/${this.articleId}`
      })
      .then(res => {
        console.log(res)
        this.articleId = res.data.articleId
        this.title = res.data.title
        this.content = res.data.content
        this.email = res.data.email
        this.createdAt = res.data.createdAt
        this.hits = res.data.hits
        this.commentList = res.data.commentList
        this.commentCnt = this.commentList.length
      })
      .catch(err => {
          console.log(err)
      })

      this.$axios({
        method:'get',
        url: `${SERVER_URL}/api/v1/communities/${this.communityId}`
      })
      .then(res => {
        console.log(res)
        this.communityId = res.data.id
        this.communityName = res.data.name
      })
      .catch(err => {
          console.log(err)
      })
    }
  }
</script>

<style scoped>
  .main-container {
    display: flex;
  }

  .article-comment-container {
    display: flex;
    flex-direction: column;
  }

  .article-container {
    padding: 2%;
    display: flex;
    width: 65vw;
    height: 55vh;
    margin-top: 10%;
    margin-left: 5%;
    flex-direction: column;
    border: 1px solid #797BF8;
  }

  .comment-container {
    margin-top: 2%;
    margin-left: 5%;
  }

  #title {
    font-size: 1.5em;
    font-weight: bold;
    padding: 0 0 1% 0;
  }

  #etc-container {
    display: flex;
    justify-content: space-between;
    padding: 0 0 3% 0;
    border-bottom: 1px solid #797BF8;
  }

  #content {
    padding: 3% 0 0 0;
    width: 100%;
    height: 60%;
    overflow: auto;
    word-break:break-all;
  }

  #update-btn {
    background-color: #797BF8;
    color: #FFFFFF;
    width: 3%;
    margin-left: 98.5%;
    margin-top: 1%;
  }

  img:hover {
    cursor: pointer;
  }
</style>