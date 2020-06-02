<template>
	<div class="menu">
		<dl>
			<dt>全部分类</dt>
			<dd v-for="(item,index) in menu"
					:key="index"
					@mouseenter="mouseEnter"
					@mouseleave="mouseLeave">
				<i :class="item.id+''" hidden >{{item.id}}</i>
				{{item.name}}
				<span class="arrow"></span>
			</dd>
		</dl>
		<div class="detail-wrapper"
				 v-if="currentId !== -1"
				 @mouseenter="detailMouseEnter"
				 @mouseleave="detailMouseLeave">
			<div class="detail" v-for="(item, index) in currentCategory.childs" :key="index">
				<div class="detail-title">
					<h2>{{item.name}}</h2>
					<a href="#">更多<i class="detail-arrow-right"></i></a>
				</div>
				<div class="detail-content">
					<a :href="'#/goods?cid='+item.id" v-for="(item, index) in item.childs" :key="index">{{item.name}}</a>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
  import { getMenu } from '/api/goods.js'
/* eslint-disable */
	export default {
		data () {
			return {
				currentId: -1,
				menu: []
			}
		},
		computed: {
			// 根据currentType值获取对应的菜单详情数据
			currentCategory() {
				return this.menu.filter(item =>	this.currentId === item.id)[0]
			}
		},
    mounted () {
		  getMenu().then(res => {
		    this.menu = res
      })
    },
    methods: {
			mouseEnter(e) {
				// 获取鼠标移入的dd节点的i子节点属性名
				clearTimeout(this.timer)
				this.currentId = parseInt(e.target.childNodes[0].className)
        // alert(typeof this.currentId)
			},
			mouseLeave() {
				this.timer =  setTimeout(() => {
					this.currentId = -1
				})
			},
			detailMouseEnter() {
				clearTimeout(this.timer)
			},
			detailMouseLeave() {
				this.currentId = -1
			}
		}
	}
</script>

<style lang="stylus" scoped>
	@import "E:\IdeaJava\anxinpha\anxinpha-front-web\src\assets\stylus\index.styl"

	.menu
		margin-left: 20px
		margin-top: 20px
		width: 230px
		height: 475px
		color: #fff
		background: linear-gradient(-180deg, rgba(2, 181, 157, .85) 2%, rgba(22, 146, 183, .85) 100%)
		dl
			dt
				padding-top: 15px
				padding-left: 15px
				box-sizing: border-box
				height: 50px
				font-size: 16px
			dd
				position: relative
				padding: 2px 12px
				box-sizing: border-box
				height: 50px
				font-size: 16px
				cursor: pointer
				&:hover
					background: rgba(255, 255, 255, .2)
					i
						opacity: 1
				i
					margin-right: 11px
					font-family: 'meituan'
					font-size: 14px
					font-style: normal
					opacity: 0.3
					&:before
						display: inline-block
						width: 14px
						height: 14px
				.food
					&:before
						content: '\e622'
				.takeout
					&:before
						content: '\e630'
				.hotel
					&:before
						content: '\e62a'
				.homestay
					&:before
						content: '\e631'
				.movie
					&:before
						content: '\e62c'
				.airport
					&:before
						content: '\e632'
				.ktv
					&:before
						content: '\e627'
				.life
					&:before
						content: '\e633'
				.hair
					&:before
						content: '\e626'
						transform: scale(0.7)
				.marry
					&:before
						content: '\e629'
				.offspring
					&:before
						content: '\e623'
				.sport
					&:before
						content: '\e62b'
				.furniture
					&:before
						content: '\e625'
				.study
					&:before
						content: '\e624'
				.health
					&:before
						content: '\e628'
				.bar
					&:before
						content: '\e621'
				.arrow
					position: absolute
					top: 11px
					right: 15px
					display: block
					width: 4px
					height: 4px
					border-top: 1px solid #fff
					border-right: 1px solid #fff
					transform: rotate(45deg)
		.detail-wrapper
			position: absolute
			box-sizing: border-box
			left: 250px
			top: 50px
			z-index: 999
			width: 400px
			height: 450px
			background: #fff
			.detail
				padding: 5px 40px 12px
				color: #ccc
				overflow: hidden
				.detail-title
					padding: 5px 0 2px
					height: 22px
					line-height: 10px
					border-bottom: 1px solid #e5e5e5
					h2
						float: left
						font-size: 16px
						font-weight: 400
						color: #222
					a
						position: relative
						float: right
						margin-right: 12px
						font-size: 12px
						color: #999
					.detail-arrow-right
						position: absolute
						right: -8px
						top: 8px
						display: block
						width: 4px
						height: 4px
						border-top: 1px solid #999
						border-right: 1px solid #999
						transform: rotate(45deg)
				.detail-content
					a
						float: left
						margin: 10px 16px 0 0
						font-size: 12px
						color: #999
						&:hover
							color: #41B883
</style>
