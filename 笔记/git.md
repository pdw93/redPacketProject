[TOC]



# git命令

- 配置用户信息

  ```
  $ git config --global user.name "John Do≤e"
  $ git config --global user.email johndoe@example.com
  查看配置
  $ git config --list
  修改
  $ git config --global --add user.name xxx
  删除
  $ git config --global --unset user.name
  
  ```

- 本地项目推送到远程

  ```
  $ git remote add origin git@github.com:linsili/test.git
  
  $ git push -u origin master
  ```

- 删除分支

  ```
  删除本地分支
  $ git branch -d xxx
  $ git branch -D xxx (强制删除)
  
  删除远程分支
  $ git push [远程名] :[分支名]
  ```

  

- 删除跟踪的文件但是不删除本地

  ```
  $ git rm --cached xxx.txt
  ```

- 查看log

  ```
  $ git log 
  -p 选项展开显示每次提交的内容差异
  -2 则仅显示最近的两次更新
  --stat 仅显示简要的增改行数统计
  --pretty=online/short/full/fuller  显示一行/显示简短信息/显示更多信息
  --author=xxx 搜索xxx作者
  --grep=xxx 搜索xxx提交说明
  ```

- git diff

  ```
  $ git diff 						->查看工作区和暂存区的差别
  $ git diff --staged		->查看暂存区和上一次commit的差异
  	--stat							->简单统计，路径，改变行数
  ```

  

- 修改最后一次未推送的提交

  ```
  $ git commit --amend
  ```

- git reset

  ```
  $ git reset HEAD  xxx	->将某个文件从暂存区恢复到工作区
  $ git reset HEAD			->将所有暂存区文件恢复到工作区
  
  $ git reset --hard HEAD^	->切换到上一次提交，重置暂存区、工作区
  $ git reset --soft HEAD^	->切换到上一次提交，保留暂存区、工作区；并把HEAD带来的差异放入暂存区
  $ git reset HEAD^					->切换到上一次提交，清空暂存区，把多差异放入工作区
  ```

- 取消修改的文件，已暂存的内容还在

  ```
  $ git checkout xxx
  
  ```

- 添加快捷键xxx

  ```
  $ git config --global alias.xxx 'yyy'
  ```

  

- **将某次提交合并到mater分支**

  ```
  git checkout master  
  git cherry-pick 82ecb31
  ```

- **合并某个分支上的一系列commits**

  首先需要基于feature创建一个新的分支，并指明新分支的最后一个commit：

  ```
  git checkout featuregit 
  git checkout -b newbranch 62ecb3
  ```

  然后，rebase这个新分支的commit到master（--ontomaster）。76cada^ 指明你想从哪个特定的commit开始。

  ```
  git rebase --onto master 76cada^ 
  ```

- stash

   保存

   ```
   git stash save '跨分支保存'
   ```

   查看某个stash

   ```
   git stash show -p stssh{1}
   ```

   恢复指定stash

   ```
   git stash apply [--index] [<stash>]		
   	--index参数：不仅恢复工作区，还恢复暂存区	
   	<stash>指定恢复某一个具体进度。如果没有这个参数，默认恢复最新进度stash@{0} 保留 stash@{0} 记录
	
   git stash pop [--index] [<stash>]   
   	--index参数：不仅恢复工作区，还恢复暂存区	
   	<stash>指定恢复某一个具体进度。如果没有这个参数，默认恢复最新进度stash@{0} 删除 stash@{0} 记录
   ```
   
   

# git flow命令

## 工作分支

1. 不使用git-flow

```
创建工作分支
git checkout develop
git checkout -b feature/feature_branch

合并分支
git checkout develop
git merge feature/feature_branch
```

2. 使用git-flow

```
创建工作分支
git flow feature start feature_branch

合并分支
git flow feature finish feature_branch
```

## 发布分支
1. 不使用git-flow

```
创建发布分支
git checkout develop
git checkout -b release/1.1.1

合并发布分支
git checkout master
git merge release/1.1.1

git checkout develop
git merge release/1.1.1
```

2. 使用git-flow

```
创建发布分支
git flow release start 1.1.1

合并发布分支
git flow release finish 1.1.1
```

## hotfix分支

1. 不使用git-flow

```
创建hotfix分支
git checkout master
git checkout -b hotfix/hotfix_branch

合并分支
git checkout master
git merge hotfix/hotfix_branch

git checkout develop
git merge hotfix/hotfix_branch

如果有release分支应该同时合并到release分支

删除hotfix分支
git branch -D hotfix/hotfix_branch
```

2. 使用git-flow

```
创建hotfix分支
git flow hotfix start hotfix_branch

合并分支
git flow hotfix finish hotfix_branch
```

## 模板
1. 不使用git-flow

```

```

2. 使用git-flow

```

```
