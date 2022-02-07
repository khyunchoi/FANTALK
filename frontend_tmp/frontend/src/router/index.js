import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../views/Index.vue'
import CommunityList from '../views/CommunityList.vue'
import CommunityListItem from '../views/CommunityListItem.vue'
import CreateArticle from '../views/CreateArticle.vue'
import DetailArticle from '../views/DetailArticle.vue'
import UpdateArticle from '../views/UpdateArticle.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
  },
  {
    path: '/communites',
    name: 'CommunityList',
    component: CommunityList,
  },
  {
    path: '/communites/:communityId',
    name: 'CommunityListItem',
    component: CommunityListItem,
  },
  {
    path: '/communites/:communityId/article/create',
    name: 'CreateArticle',
    component: CreateArticle,
  },
  {
    path: '/communites/:communityId/article/:articleId',
    name: 'DetailArticle',
    component: DetailArticle,
  },
  {
    path: '/communites/:communityId/article/:articleId/update',
    name: 'UpdateArticle',
    component: UpdateArticle,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
