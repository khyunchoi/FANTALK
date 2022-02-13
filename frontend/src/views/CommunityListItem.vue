<template>
  <div style="display:flex; flex-direction: row">

    <div class="community-list-item-side" style="width: 30%;">
      <br><br><br><br>
      <div class="community-list-item-title">
        <h3>{{ communityName }} 팬 커뮤니티</h3>
        <br>
        <h5>{{ communityTitle }}</h5>
      </div>
    </div>

    <div class="community-list-item" style="width: 60%;">
      <br><br>
      <div style="display: flex; width: 60%;">
        <div style="width: 100%; margin-right: 10px;">
          <v-text-field
            label="제목"
            dense
            solo
            v-model="q"
            @keyup.enter="search()"
          >
          </v-text-field>
        </div>
        <v-btn
          rounded
          @click="search()"
          style="background-color: #797BF8; color: white; height: 38px;"
        >
          검색
        </v-btn>
      </div>

      <div>
          <v-btn v-for="searchResult in searchResults" :key="searchResult" @click="enterDetailArticle(searchResult.articleId)" style="margin: 0px 10px 0px 0px;">
            {{ searchResult.title }}
          </v-btn>
      </div>


      <br>
      <div class="community-list-item-list">
        <div class="community-list-item-list-articles-1" style="padding-bottom: 5px; border-style: solid; border-width: 0px 0px 2px 0px; border-color: gray;">
          <span style="width: 10%" class="community-list-item-list-articles-elements">번호</span>
          <span style="width: 50%" class="community-list-item-list-articles-elements">제목</span>
          <span style="width: 30%" class="community-list-item-list-articles-elements">작성</span>
          <span style="width: 10%" class="community-list-item-list-articles-elements">조회수</span>
        </div>
        <button @click="enterDetailArticle(article.articleId)" class="community-list-item-list-articles-2" v-for="article in articles" :key="article">
          <span style="width: 10%" class="community-list-item-list-articles-elements">{{ article.articleId }}</span>
          <span style="width: 50%" class="community-list-item-list-articles-elements">{{ article.title }}</span>
          <span style="width: 30%" class="community-list-item-list-articles-elements">{{ article.createdAt.slice(0,19) }}</span>
          <span style="width: 10%" class="community-list-item-list-articles-elements">{{ article.hits }}</span>
        </button>
      </div>

      <br>

      <div class="community-list-item-create">
        <v-btn
          rounded
          @click="enterCreateArticle()"
          style="background-color: #797BF8; color: white; height: 30px;"
        >
          글쓰기
        </v-btn>
      </div>


    </div>

    <div class="community-list-item-side" style="width: 10%;">

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
        communityTitle: null,
        searchResults: null,
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

      },
      search() {
        this.$axios({
          method: 'get',
          url: `${SERVER_URL}/api/v1/communities/${this.communityId}/search`,
          params: {
            q: this.q,
          }
        })
        .then(res => {
          this.searchResults = res.data
          console.log(this.searchResults)
        })
        .catch(err => {
          console.log(err)
        })
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
      this.$axios({
        method:'get',
        url: `${SERVER_URL}/api/v1/communities/${this.communityId}`
      })
      .then(res => {
        console.log(res.data)
        return res.data
      })
      .then(res => {
        this.communityName = res.name
        this.communityTitle = res.title
        return res
      })
      .catch(error => {
          console.log(error)
      })
    }
  }
</script>

<style>
  .community-list-item-side {
    display: flex;
    /* background-color: beige; */
    padding: 4%;
    flex-direction: column;
  }
  .community-list-item {
    display: flex;
    /* background-color: beige; */
    padding: 4%;
    flex-direction: column;
  }
  .community-list-item-title {
    display: flex;
    flex-direction: column;
    /* background-color: bisque; */
    margin: 10px;
  }
  .community-list-item-list {
    display: flex;
    flex-direction: column;
  }
  .community-list-item-list-articles-1 {
    display: flex;
    width: 100%;
    flex-direction: row;
    justify-content: space-between;
    margin: 10px;
  }
  .community-list-item-list-articles-2 {
    display: flex;
    width: 100%;
    flex-direction: row;
    justify-content: space-between;
    margin: 10px;
  }
  .community-list-item-list-articles-2:hover {
    transition: 0.2s;
    transform: translateY(-2px);
    color: gray;
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