import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: () => import(/* webpackChunkName: "about" */ '../components/HomePage.vue')
    },
    {
        path: '/mySubscriptions',
        name: 'MySubscriptions',
        component: () => import(/* webpackChunkName: "about" */ '../components/MySubscriptions.vue')
    },
    {
        path: '/users/register',
        name: 'Register',
        component: () => import(/* webpackChunkName: "about" */ '../components/Register.vue')
    },
    {
        path: '/users/login',
        name: 'Login',
        component: () => import(/* webpackChunkName: "about" */ '../components/Login.vue')
    }
];

const index = new VueRouter({
    mode: 'history',
    routes
});

export default index