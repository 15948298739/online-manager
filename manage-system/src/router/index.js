import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/user/User.vue'
import AddUser from '../components/user/AddUser.vue'
import AddItem from '../components/item/addItem.vue'
import Item from '../components/item/Item.vue'
import ItemCat from '../components/item/ItemCat.vue'
import ItemCatParam from '../components/item/ItemCatParam.vue'
import Info from '../components/UserInfo.vue'
//使用路由机制
Vue.use(VueRouter)
const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home', component: Home,
    redirect: '/welcome', children: [
      { path: '/welcome', component: Welcome },
      { path: '/user', component: User },
      { path: '/info', component: Info },
      { path: '/addUser', component: AddUser },
      { path: '/itemCat', component: ItemCat },
      { path: '/item', component: Item },
      { path: '/item/addItem', component: AddItem },
      { path: '/itemCatParam', component: ItemCatParam }
    ]
  }
]

const router = new VueRouter({
  routes
})

//路由导航守卫!!!!!!!
router.beforeEach((to, from, next) => {
  // 这里先获取下用户信息，我偷懒用sessionStorage存了
  const token = window.sessionStorage.getItem("token");
  // 里面包含了用户权限，用户各种信息等
  // 这里必须加上to.path !== 'login'的判断，不然会陷入无限循环，
  // 因为逻辑是第一次进来，判断用户信息不存在，即!user为true，由于使用的是next('/login')而非next()，
  // 会再次进入路由跳转，next()方法没有参数是直接进入页面，不会再次进入路由拦截，有参数则会，因为跳转，
  // 所以再次进入路由，再次判断，再次进入路由，再次判断，循环往复无限循环
  // 所以一定要加to.path !== 'login'的判断
  if (token == null && to.path !== '/login') {
    next('/login');
  } else {
    next();
  }
})

export default router
