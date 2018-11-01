//
//  ViewController.swift
//  ExamenIOS
//
//  Created by Marco Antonio Martínez Gutiérrez on 25/10/18.
//  Copyright © 2018 Marco Antonio Martínez Gutiérrez. All rights reserved.
//

import UIKit




class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{
    
    
    var presenter:ViewToPresenterProtocol!
    
    
    private var news:[HackerContent] = []
    @IBOutlet weak var myTable: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(presenter)
        presenter.updateView()
        
        myTable.delegate = self
        myTable.dataSource = self
        
        self.view.showBlurLoader()
        
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return news.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell =
            tableView.dequeueReusableCell(withIdentifier: "CustomCell", for: indexPath) as! CustomCell
        
        
        cell.title!.text = news[indexPath.row].title
        cell.content.text = news[indexPath.row].by
        /*networker.getArticle(id: news[indexPath.row]){ result, error in
            cell.title!.text = result.title
            cell.content.text = result.about
            
        }*/
        
        
        
        return  cell
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let url = URL(string: news[indexPath.row].url) {
            UIApplication.shared.open(url, options: [:])
        }
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 70
    }
    

    
    

}

class CustomCell: UITableViewCell{
    
    
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var content: UILabel!
    
    
    
}




extension ViewController: PresenterToViewProtocol{
    func showError() {
        
    }
    
    func showNews(news: [HackerContent]) {
        self.news = news
        self.view.removeBluerLoader()
        myTable.reloadData()
    }
    
    
}

extension UIImageView {
    func downloadedFrom(url: URL, contentMode mode: UIView.ContentMode = .scaleAspectFit) {
        contentMode = mode
        
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard
                let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                let data = data, error == nil,
                let image = UIImage(data: data)
                else { return }
            DispatchQueue.main.async() {
                self.image = image
            }
            }.resume()
    }
    func downloadedFrom(link: String, contentMode mode: UIView.ContentMode = .scaleAspectFit) {
        guard let url = URL(string: link) else { return }
        downloadedFrom(url: url, contentMode: mode)
    }
}


extension UIView{
    func showBlurLoader(){
        let blurEffect = UIBlurEffect(style: UIBlurEffect.Style.dark)
        let blurEffectView = UIVisualEffectView(effect: blurEffect)
        blurEffectView.frame = self.bounds
        blurEffectView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        
        let activityIndicator = UIActivityIndicatorView(style: .whiteLarge)
        activityIndicator.frame = CGRect(x: 0, y: 0, width: 50, height: 50)
        activityIndicator.startAnimating()
        
        blurEffectView.contentView.addSubview(activityIndicator)
        activityIndicator.center = blurEffectView.contentView.center
        
        self.addSubview(blurEffectView)
    }
    
    func removeBluerLoader(){
        self.subviews.flatMap {  $0 as? UIVisualEffectView }.forEach {
            $0.removeFromSuperview()
        }
    }
}
